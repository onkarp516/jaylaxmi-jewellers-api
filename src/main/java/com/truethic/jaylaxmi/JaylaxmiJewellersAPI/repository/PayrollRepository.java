package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import java.io.ByteArrayInputStream;
import java.util.Map;

public interface PayrollRepository {
    ByteArrayInputStream exportReceiptPdf(String templateName, Map<String, Object> data);
}
