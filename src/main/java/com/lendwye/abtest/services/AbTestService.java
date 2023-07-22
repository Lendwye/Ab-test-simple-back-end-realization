package com.lendwye.abtest.services;

import com.lendwye.abtest.enums.ButtonColorOptions;
import com.lendwye.abtest.enums.PurchaseCostOptions;
import com.lendwye.abtest.models.DeviceTokenData;
import com.lendwye.abtest.models.Experiment;
import com.lendwye.abtest.repositories.AbTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AbTestService {

    private final AbTestRepository repository;

    @Autowired
    public AbTestService(AbTestRepository abTestRepository)
    {
        this.repository = abTestRepository;
    }

    public String getButtonColorName() {
        ButtonColorOptions[] buttonColorOptions = ButtonColorOptions.values();
        Map<String, Object> frequency_of_options = new HashMap<>();

        for (ButtonColorOptions buttonColorOption : buttonColorOptions) {
            String buttonColorOptionStr = buttonColorOption.toString();
            frequency_of_options.put(buttonColorOptionStr, this.getAmountOfRecordsWithCertainColor(buttonColorOptionStr));
        }

        frequency_of_options = frequency_of_options.entrySet().stream()
                .map((entry) -> {
                    ButtonColorOptions buttonColorOption = ButtonColorOptions.valueOf(entry.getKey());
                    float newEntryValue = buttonColorOption.getFrequency() / this.getAmountOfRecordsWithCertainColor(buttonColorOption.toString());
                    entry.setValue(newEntryValue);
                    return entry;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        frequency_of_options = frequency_of_options.entrySet().stream()
                .sorted(Comparator.comparing(entry -> (Float) entry.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
        ));

        for (Map.Entry<String, Object> entry : frequency_of_options.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        String lastKey = "";
        Iterator<Map.Entry<String, Object>> iterator = frequency_of_options.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            lastKey = entry.getKey();
        }
        return lastKey;
    }

    public long getAmountOfRecordsWithCertainColor(String color)
    {

        return repository.countByButtonColorName(color);
    }

    public String getPurchaseCostName()
    {
        PurchaseCostOptions[] purchaseCostOptions = PurchaseCostOptions.values();
        Map<String, Object> frequency_of_options = new HashMap<>();

        for (PurchaseCostOptions purchaseCostOption : purchaseCostOptions) {
            String purchaseCostOptionStr = purchaseCostOption.toString();
            frequency_of_options.put(purchaseCostOptionStr, this.getAmountOfRecordsWithCertainPurchaseCost(purchaseCostOptionStr));
        }

        frequency_of_options = frequency_of_options.entrySet().stream()
                .map((entry) -> {
                    PurchaseCostOptions purchaseCostOption = PurchaseCostOptions.valueOf(entry.getKey());
                    float newEntryValue = purchaseCostOption.getFrequency() / this.getAmountOfRecordsWithCertainPurchaseCost(purchaseCostOption.toString());
                    entry.setValue(newEntryValue);
                    return entry;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        frequency_of_options = frequency_of_options.entrySet().stream()
                .sorted(Comparator.comparing(entry -> (Float) entry.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
        ));

        for (Map.Entry<String, Object> entry : frequency_of_options.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        String lastKey = "";
        Iterator<Map.Entry<String, Object>> iterator = frequency_of_options.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            lastKey = entry.getKey();
        }
        return lastKey;
    }

    public long getAmountOfRecordsWithCertainPurchaseCost(String purchaseColorName)
    {
        return repository.countByPurchaseCostName(purchaseColorName);
    }

    public boolean repositoryContainsDeviceToken(Long deviceToken)
    {
        return repository.findByDeviceToken(deviceToken) != null;
    }

    public DeviceTokenData getDeviceTokenData(Long deviceToken)
    {
        return repository.findByDeviceToken(deviceToken);
    }

    public DeviceTokenData saveDeviceTokenData(Long deviceToken)
    {
        DeviceTokenData deviceTokenData = new DeviceTokenData(deviceToken);

        String deviceTokenButtonColorName = this.getButtonColorName();
        String deviceTokenButtonColor = ButtonColorOptions.valueOf(deviceTokenButtonColorName).getValue();

        deviceTokenData.setButtonColorName(deviceTokenButtonColorName);
        deviceTokenData.setButtonColor(deviceTokenButtonColor);

        String deviceTokenPurchaseName = this.getPurchaseCostName();
        Long deviceTokenPurchase = PurchaseCostOptions.valueOf(deviceTokenPurchaseName).getValue();

        deviceTokenData.setPurchaseCostName(deviceTokenPurchaseName);
        deviceTokenData.setPurchaseCost(deviceTokenPurchase);

        repository.save(deviceTokenData);
        return deviceTokenData;
    }
}
