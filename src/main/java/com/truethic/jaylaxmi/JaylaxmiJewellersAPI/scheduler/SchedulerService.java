package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.scheduler;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Employee;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.AttendanceRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.EmployeeRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.TaskMasterRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.WorkBreakRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Attendance;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.TaskMaster;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.WorkBreak;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service.AttendanceService;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service.TaskService;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class SchedulerService {
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private TaskMasterRepository taskMasterRepository;
    @Autowired
    private Utility utility;
    @Autowired
    private WorkBreakRepository workBreakRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void checkEmployeeOutTime() throws ParseException {
        List<Attendance> attendanceList = attendanceRepository.findByCheckOutTimeIsNull();
        System.out.println("Attendance size " + attendanceList.size());
        if (attendanceList.size() > 0) {
            for (Attendance attendance : attendanceList) {
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = attendance.getEmployee().getShift().getToTime();
                LocalDateTime checkOutTime = LocalDateTime.of(currentDate,currentTime);
                System.out.println("Attendance Id: " + attendance.getId());
//                Integer hours = checkDateTimeDiffInTime(attendance.getCheckInTime(), checkOutTime);
//                System.out.println("hours " + hours);
//                LocalTime timeToCompare = attendance.getEmployee().getShift().getThreshold();
                LocalTime latePunchTime = LocalTime.of(14,30,00);
//                if (hours >= 8) {
                    try {
                        endAllRemainingTasks(attendance);
//                        attendance.setTotalTime(totalTime);
                        attendance.setCheckOutTime(checkOutTime);
                        attendance.setUpdatedAt(LocalDateTime.now());

                        String[] timeParts = utility.getDateTimeDiffInTime(attendance.getCheckInTime(), checkOutTime).toString().split(":");
                        int hour = Integer.parseInt(timeParts[0]);
                        int minutes = Integer.parseInt(timeParts[1]);
                        double workedHours = utility.getTimeInDouble(hour+":"+minutes);
                        if(attendance.getCheckInTime().toLocalTime().compareTo(latePunchTime) > 0 || workedHours < 6) {
                            attendance.setIsHalfDay(true);
                        }

                        LocalTime totalTime = LocalTime.parse("00:00:00");
                        LocalDateTime firstTaskStartTime = null;
                        LocalDateTime lastTaskEndTime = null;

                        if (attendance.getEmployee().getDesignation().getCode().equalsIgnoreCase("l3") ||
                                attendance.getEmployee().getDesignation().getCode().equalsIgnoreCase("l2")) {
                            /*From Task Data*/
                            firstTaskStartTime = taskMasterRepository.getInTime(attendance.getId());
                            System.out.println("firstTaskStartTime =>>>>>>>>>>>>>>>>>>>>>>" + firstTaskStartTime);
                            lastTaskEndTime = taskMasterRepository.getOutTime(attendance.getId());
                            System.out.println("lastTaskEndTime =>>>>>>>>>>>>>>>>>>>>>>" + lastTaskEndTime);
                            if (firstTaskStartTime != null && !firstTaskStartTime.equals("null") && lastTaskEndTime != null && !lastTaskEndTime.equals("null")) {
                                totalTime = utility.getDateTimeDiffInTime(firstTaskStartTime, lastTaskEndTime);
                                System.out.println("totalTime =>>>>>>>>>>>>>>>>>>>>>>" + totalTime);
                                attendance.setTotalTime(totalTime);
                            } else {
                                firstTaskStartTime = LocalDateTime.now();
                                lastTaskEndTime = firstTaskStartTime;
                                totalTime = utility.getDateTimeDiffInTime(firstTaskStartTime, lastTaskEndTime);
                                System.out.println("totalTime =>>>>>>>>>>>>>>>>>>>>>>" + totalTime);
                                attendance.setTotalTime(totalTime);
                            }
                        } else {
                            firstTaskStartTime = attendance.getCheckInTime();
                            lastTaskEndTime = checkOutTime;
                            /* From Attendance Data */
                            totalTime = utility.getDateTimeDiffInTime(attendance.getCheckInTime(), checkOutTime);
                            System.out.println("totalTime =>>>>>>>>>>>>>>>>>>>>>>" + totalTime);
                            attendance.setTotalTime(totalTime);
                        }

                        double actualWorkTime = taskMasterRepository.getSumOfActualWorkTime(attendance.getId());
                        attendance.setActualWorkTime(actualWorkTime);
                        double lunchTimeInMin = 0;
                        LocalTime lunchTime = null;

                        WorkBreak workBreak = workBreakRepository.findByBreakName(attendance.getInstitute().getId());
                        TaskMaster taskMaster = null;
                        if (workBreak != null) {
                            lunchTimeInMin = taskMasterRepository.getSumOfLunchTime(attendance.getId(), workBreak.getId(), true, false);
                            /*taskMaster = taskMasterRepository.findByAttendanceIdAndWorkBreakIdAndStatusAndWorkDone(attendance.getId(), workBreak.getId(), true, false);
                            if (taskMaster != null) {
                                lunchTimeInMin = taskMaster.getTotalTime();
                                lunchTime = taskMaster.getWorkingTime();
                            }*/
                        }
                        attendance.setLunchTime(lunchTimeInMin);

                        int s = 0;
                        int attendanceSec = 0;
                        double totalMinutes = 0;
                        double attendanceMinutes = 0;
                        if (totalTime != null) {
                            // OLD CODE s = (int) SECONDS.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
                            s = (int) SECONDS.between(firstTaskStartTime, lastTaskEndTime);
                            attendanceSec = Math.abs(s);
                            System.out.println("attendanceSec " + attendanceSec);
                            attendanceMinutes = ((double) attendanceSec / 60.0);
                            totalMinutes = attendanceMinutes - lunchTimeInMin;

                            System.out.println("attendanceMinutes " + attendanceMinutes);
                            System.out.println("totalMinutes " + totalMinutes);

                            double workingHours = totalMinutes > 0 ? (totalMinutes / 60.0) : 0;
                            double wagesHourBasis = attendance.getWagesPerHour() * workingHours;
                            System.out.println("workingHours " + workingHours);
                            System.out.println("wagesPerHour " + attendance.getWagesPerHour());
                            System.out.println("wagesHourBasis " + wagesHourBasis);

                            attendance.setWorkingHours(workingHours);
                            attendance.setWagesHourBasis(wagesHourBasis);
                        }

                        Attendance attendance1 = attendanceRepository.save(attendance);
//                        attendanceService.updateSalaryForDay(attendance1);
                        System.out.println("check out updated");
                    } catch (Exception e) {
                        System.out.println("Exception " + e);
                    }
//                }
            }
        }
    }

    private void endAllRemainingTasks(Attendance attendance) throws ParseException {
        List<TaskMaster> taskList = taskMasterRepository.findByAttendanceIdAndStatusAndEndTimeIsNull(attendance.getId(), true);

        for (TaskMaster task : taskList) {
            if (task != null) {
                Integer taskType = task.getTaskType();
                task.setTaskStatus("complete");

                LocalDateTime l1 = task.getStartTime();
                LocalDateTime l2 = LocalDateTime.now();
                task.setEndTime(l2);
                System.out.println("SECONDS To MINUTES " + (SECONDS.between(l1, l2) / 60));
                double totalTime = SECONDS.between(l1, l2) / 60.0;
                double time = totalTime;
                System.out.println("total time in min " + time);
                task.setTotalTime(time);
                task.setActualWorkTime(time);

                LocalTime workTime = utility.getDateTimeDiffInTime(l1, l2);
                task.setWorkingTime(workTime);

                Employee employee = employeeRepository.findByIdAndStatus(task.getEmployee().getId(), true);
                LocalTime shiftTime = employee.getShift().getWorkingHours();
                Integer shiftMinutes = ((shiftTime.getHour() * 60) + shiftTime.getMinute());
                System.out.println("shiftMinutes in min " + shiftMinutes);
                Double shiftHours = Double.valueOf((shiftMinutes / 60));
                System.out.println("shiftHours" + shiftHours);
                System.out.println("user time in minutes 2=>Downtime" + time);
                task.setBreakWages(taskService.calculateBreakData(time, task, shiftHours));
//                if (taskType == 3) { // 3=>Setting time
//                    System.out.println("user time in minutes 2=>Setting time" + time);
//                    task.setActualWorkTime(time);
//                    task.setBreakWages(taskService.calculateBreakData(time, task, shiftHours));
//                } else if (taskType == 1) { // 1=>Task
//                    System.out.println("user task type for 2=>Task is skipped");
//                } else if (taskType == 2) { // 2=>Downtime
//                    System.out.println("user time in minutes 2=>Downtime" + time);
//                    task.setBreakWages(taskService.calculateBreakData(time, task, shiftHours));
//                } else if (taskType == 4) { // 4=> task without machine
//                    System.out.println("user time in minutes 4=> task without machine " + time);
//
//                    double totalBreakMinutes = taskMasterRepository.getSumOfBreakTime(task.getId());
//                    double actualTaskTime = time - totalBreakMinutes;
//                    task.setActualWorkTime(actualTaskTime);
//                    double workHours = (time / 60.0);
//                    double prodWorkHours = (actualTaskTime / 60.0);
//                    task.setWorkingHour(workHours);
//                    task.setProdWorkingHour(prodWorkHours);
//                    double settingTimeInMinutes = totalBreakMinutes;
//                    double perMinutePoint = 100.0 / 60;
//                    double settingTimeInHour = perMinutePoint * settingTimeInMinutes / 100.0;
//
//                    task.setSettingTimeInMin(settingTimeInMinutes);
//                    task.setSettingTimeInHour(settingTimeInHour);
//                    task.setActualWorkTime(time);
//                }

                task.setUpdatedAt(LocalDateTime.now());
                try {
                    TaskMaster savedTaskMaster = taskMasterRepository.save(task);
                    taskService.updateEmployeeTaskSummary(attendance);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("auto end task Exception " + e.getMessage());
                }
            }
        }
    }


    public Integer checkDateTimeDiffInTime(LocalDateTime fromDate, LocalDateTime toDate) throws ParseException {
        System.out.println("fromDate " + fromDate);
        System.out.println("toDate " + toDate);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date d1 = df.parse(fromDate.toString());
        Date d2 = df.parse(toDate.toString());
        long d = d2.getTime() - d1.getTime();
        long hh = d / (3600 * 1000);
        long mm = (d - hh * 3600 * 1000) / (60 * 1000);
        System.out.printf("\n %02d:%02d \n", hh, mm);

        Integer totalTime = 0;
        if (hh > 23) {
            totalTime = 16;
        } else {
            totalTime = Math.toIntExact(hh);
        }
        System.out.println("totalTime " + totalTime);
        return totalTime;
    }
}
