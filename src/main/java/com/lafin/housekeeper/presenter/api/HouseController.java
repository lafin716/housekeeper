package com.lafin.housekeeper.presenter.api;

import com.lafin.housekeeper.presenter.api.adapter.HouseAdapter;
import com.lafin.housekeeper.presenter.api.adapter.UserAdapter;
import com.lafin.housekeeper.presenter.api.request.CreateHouseRequest;
import com.lafin.housekeeper.presenter.api.request.UpdateHouseRequest;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.common.Paging;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/house")
public class HouseController {

    private final UserAdapter userAdapter;

    private final HouseAdapter houseAdapter;

    @GetMapping("/list/{page}")
    public ResponseEntity<ResponseModel> list(@PathVariable int page,
                                              @RequestHeader(value = "X-AUTH-TOKEN") String accessToken) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        var paging = Paging.of(page);
        return ResponseUtil.json(houseAdapter.getHouses(userId, paging));
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<ResponseModel> list(@PathVariable Long houseId,
                                              @RequestHeader(value = "X-AUTH-TOKEN") String accessToken) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        return ResponseUtil.json(houseAdapter.getHouse(userId, houseId));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModel> add(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                             @RequestBody CreateHouseRequest request) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        request.setUserId(userId);
        return ResponseUtil.json(houseAdapter.addHouse(request));
    }

    @PutMapping("/{houseId}")
    public ResponseEntity<ResponseModel> update(@PathVariable Long houseId,
                                                @RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                                @RequestBody UpdateHouseRequest request) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        request.setUserId(userId);
        request.setHouseId(houseId);
        return ResponseUtil.json(houseAdapter.updateHouse(request));
    }

    @DeleteMapping("/{houseId}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long houseId,
                                                @RequestHeader(value = "X-AUTH-TOKEN") String accessToken) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        return ResponseUtil.json(houseAdapter.deleteHouse(userId, houseId));
    }
}
