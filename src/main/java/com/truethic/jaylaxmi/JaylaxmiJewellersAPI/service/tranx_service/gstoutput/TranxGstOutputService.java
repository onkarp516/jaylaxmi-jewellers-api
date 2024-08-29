package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service.tranx_service.gstoutput;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.common.GenerateFiscalYear;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.ledgerdetails_repo.LedgerMasterRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo.PaymentModeMasterRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo.TaxMasterRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.gstoutput_repository.GstOutputDetailsRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.gstoutput_repository.GstOutputDutiesTaxesRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.gstoutput_repository.GstOutputMasterRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.util.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranxGstOutputService {
    @Autowired
    private JwtTokenUtil jwtRequestFilter;
    @Autowired
    private GstOutputMasterRepository gstOutputMasterRepository;
    @Autowired
    private GstOutputDetailsRepository gstOutputDetailsRepository;
    @Autowired
    private GenerateFiscalYear generateFiscalYear;

    private static final Logger gstOutpuLogger = LogManager.getLogger(TranxGstOutputService.class);
    @Autowired
    private LedgerMasterRepository ledgerMasterRepository;
    @Autowired
    private PaymentModeMasterRepository paymentModeMasterRepository;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private ProductHsnRepository productHsnRepository;
    @Autowired
    private TaxMasterRepository taxMasterRepository;
    @Autowired
    private GstOutputDutiesTaxesRepository gstOutputDutiesTaxesRepository;
   /* public JsonObject gstOutputLastRecord(HttpServletRequest request) {
        Users users = jwtRequestFilter.getUserDataFromToken(
                request.getHeader("Authorization").substring(7));
        Long count = 0L;
        if (users.getBranch() != null) {
            count = gstOutputMasterRepository.findBranchLastRecord(users.getOutlet().getId(), users.getBranch().getId());
        } else {
            count = gstOutputMasterRepository.findLastRecord(users.getOutlet().getId());
        }
        String serailNo = String.format("%05d", count + 1);// 5 digit serial number
        GenerateDates generateDates = new GenerateDates();
        String currentMonth = generateDates.getCurrentMonth().substring(0, 3);
        String csCode = "GSTINPUT" + currentMonth + serailNo;
        JsonObject result = new JsonObject();
        result.addProperty("message", "success");
        result.addProperty("responseStatus", HttpStatus.OK.value());
        result.addProperty("count", count + 1);
        result.addProperty("gstInputNo", csCode);
        return result;
    }

    public JsonObject createGstOutput(HttpServletRequest request) {
    }

    public JsonObject gstOutputList(HttpServletRequest request) {
    }

    public JsonObject updateGstOutput(HttpServletRequest request) {
    }

    public JsonObject getGstOutputById(HttpServletRequest request) {
    }*/
}
