package com.lendwye.abtest.repositories;

import com.lendwye.abtest.models.DeviceTokenData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AbTestRepository extends JpaRepository<DeviceTokenData, Long> {

    @Query("SELECT e FROM DeviceTokenData e WHERE e.device_token = :device_token")
    DeviceTokenData findByDeviceToken(@Param("device_token") Long deviceToken);

    @Query("SELECT COUNT(e) FROM DeviceTokenData e WHERE e.purchase_cost_name = :price_name")
    long countByPurchaseCostName(@Param("price_name") String price_name);

    @Query("SELECT COUNT(e) FROM DeviceTokenData e WHERE e.button_color_name = :button_color_name")
    long countByButtonColorName(@Param("button_color_name") String button_color_name);
}
