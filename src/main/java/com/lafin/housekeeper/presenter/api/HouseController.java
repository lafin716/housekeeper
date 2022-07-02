package com.lafin.housekeeper.presenter.api;

import com.lafin.housekeeper.presenter.api.adapter.HouseAdapter;
import com.lafin.housekeeper.presenter.api.adapter.UserAdapter;
import com.lafin.housekeeper.presenter.api.request.CreateHouseRequest;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.Paging;
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
        userAdapter.verify(accessToken);
        var paging = Paging.builder()
                .page(page)
                .build();
        return ResponseUtil.json(houseAdapter.getHouses(paging));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModel> add(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                             @RequestBody CreateHouseRequest request) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        request.setUserId(userId);
        return ResponseUtil.json(houseAdapter.addHouse(request));
    }
}
