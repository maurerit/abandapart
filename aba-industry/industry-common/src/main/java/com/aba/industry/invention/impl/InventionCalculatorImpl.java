package com.aba.industry.invention.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.invention.InventionCalculator;

/**
 * @author maurerit
 */
@Component
public class InventionCalculatorImpl implements InventionCalculator {
    @Autowired
    private BuildRequirementsProvider buildReqProvider;
}
