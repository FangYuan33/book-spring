package framework.spring.service.impl;

import framework.spring.service.MoneyValidator;

public class MoneyValidatorImpl implements MoneyValidator {
    @Override
    public boolean validate(int money) {
        return money > 0;
    }
}
