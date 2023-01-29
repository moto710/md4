package com.ajaxbankingtransaction.model.dto;


import com.ajaxbankingtransaction.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LocationRegionDTO implements Validator {

    private Integer id;
    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;
    private String address;

    public LocationRegion toLocationRegion() {
        return new LocationRegion()
                .setId(id)
                .setProvinceId(provinceId)
                .setProvinceName(provinceName)
                .setDistrictId(districtId)
                .setDistrictName(districtName)
                .setWardId(wardId)
                .setWardName(wardName)
                .setAddress(address);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LocationRegionDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LocationRegionDTO locationRegionDTO = (LocationRegionDTO) target;
//        String provinceId = locationRegionDTO.getProvinceId();
//        String provinceName = locationRegionDTO.getProvinceName();
//        String districtId = locationRegionDTO.getDistrictId();
//        String districtName = locationRegionDTO.getDistrictName();
//        String wardId = locationRegionDTO.getWardId();
//        String wardName = locationRegionDTO.getWardName();
        String address = locationRegionDTO.getAddress();

        if (address.length() == 0) {
            errors.rejectValue("address", "address.null", "Please fill your address!");
        } else if (address.length() < 5) {
            errors.rejectValue("address", "address.length", "Address must be more than 5 letters!");
        }
    }
}
