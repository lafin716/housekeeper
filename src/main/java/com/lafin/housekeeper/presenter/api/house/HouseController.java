package com.lafin.housekeeper.presenter.api.house;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/house")
public class HouseController {

    private final HouseAdapter houseAdapter;

    @GetMapping("/")
    public ResponseEntity<ResponseModel> list() {
        return ResponseUtil.json(houseAdapter.getHouses());
    }
}
