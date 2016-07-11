package com.aba.industry.invention.impl;

import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.invention.InventionCalculator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author maurerit
 */
public class InventionCalculatorImpl implements InventionCalculator {
    @Autowired
    private BuildRequirementsProvider buildReqProvider;
}
