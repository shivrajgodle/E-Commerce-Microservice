package com.shivraj.ecommerce.handler;

import java.util.Map;

public record ErrorResponse(Map<String, String> error) {
}
