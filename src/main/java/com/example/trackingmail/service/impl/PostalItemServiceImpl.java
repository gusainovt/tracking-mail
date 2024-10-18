package com.example.trackingmail.service.impl;

import com.example.trackingmail.exception.PostalItemNotFoundException;
import com.example.trackingmail.mapper.PostalItemMapper;
import com.example.trackingmail.mapper.PostalOfficeMapper;
import com.example.trackingmail.mapper.StatusMapper;
import com.example.trackingmail.model.PostalItem;
import com.example.trackingmail.model.PostalOffice;
import com.example.trackingmail.model.Status;
import com.example.trackingmail.model.dto.postalItem.PostalItemDto;
import com.example.trackingmail.model.dto.postalItem.RegisterItemDto;
import com.example.trackingmail.model.dto.postalOffice.PostalOfficeDto;
import com.example.trackingmail.model.dto.status.StatusDto;
import com.example.trackingmail.repository.PostalItemRepository;
import com.example.trackingmail.service.PostalItemService;
import com.example.trackingmail.service.PostalOfficeService;
import com.example.trackingmail.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.trackingmail.model.Status.PostalStatus.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class PostalItemServiceImpl implements PostalItemService {

    private final PostalItemRepository itemRepository;

    private final PostalOfficeService officeService;

    private final StatusService statusService;

    private final PostalItemMapper itemMapper;

    private final PostalOfficeMapper officeMapper;

    private final StatusMapper statusMapper;

    /**
     * Registration of the postal item
     * @param registerItemDto {@link RegisterItemDto} Information of the postal item and the initial post office.
     * @return {@link PostalItemDto}
     */
    @Override
    public PostalItemDto createPostalItem(RegisterItemDto registerItemDto) {
        log.info("Method called: createPostalItem");
        PostalItem postalItem = itemRepository.save(itemMapper.registerItemDtoToPostalItem(registerItemDto));
        PostalOffice postalOffice = officeService.createPostalOffice(
                officeMapper.registerItemDtoToPostalOffice(registerItemDto));
        Status status = statusService.createStatus(postalOffice, PENDING);
        log.info("The postal item created");
        return itemMapper.postalItemToPostalItemDto(
                itemRepository.save(addStatus(status, postalItem)));
    }

    /**
     * Arrival of the postal item at the post office
     * @param id The postal item ID
     * @param postalOfficeDto {@link PostalOfficeDto} Information of the post office
     * @return {@link PostalItemDto}
     */
    @Override
    public PostalItemDto arrivePostalItem(Long id, PostalOfficeDto postalOfficeDto) {
        log.info("Method called: arrivePostalItem");
        PostalItem currentItem = getPostalItem(id);
        PostalOffice currentOffice = officeService.createPostalOffice(
                officeMapper.postalOfficeDtoToPostalOffice(postalOfficeDto));
        Status status = statusService.createStatus(currentOffice, ARRIVED);
        log.info("The postal item arrived at: " + currentOffice.getName());
        return itemMapper.postalItemToPostalItemDto(
                itemRepository.save(addStatus(status, currentItem)));
    }

    /**
     * Departure of the postal item from the post office
     * @param id The postal item ID
     * @return {@link PostalItemDto}
     */
    @Override
    public PostalItemDto departurePostalItem(Long id) {
        log.info("Method called: departurePostalItem");
        PostalItem currentItem = getPostalItem(id);
        PostalOffice currentOffice = officeMapper.statusDtoToPostalOffice(getCurrentStatus(id));
        Status status = statusService.createStatus(currentOffice, DEPARTURE);
        log.info("The postal item has departed from: " + currentOffice.getName());
        return itemMapper.postalItemToPostalItemDto(
                itemRepository.save(addStatus(status, currentItem)));
    }

    /**
     * Receiving the postal item by the addressee
     * @param id The postal item ID
     * @return {@link PostalItemDto}
     */
    @Override
    public PostalItemDto receivePostalItem(Long id) {
        log.info("Method called: receivePostalItem");
        PostalItem currentItem = getPostalItem(id);
        PostalOffice postalOffice = new PostalOffice();
        postalOffice.setName(currentItem.getRecipientName());
        postalOffice.setAddress(currentItem.getRecipientAddress());
        postalOffice.setIndex(currentItem.getRecipientIndex());
        officeService.createPostalOffice(postalOffice);

        Status status = statusService.createStatus(postalOffice, DELIVERED);
        log.info("The postal item received at: " + postalOffice.getName());
        return itemMapper.postalItemToPostalItemDto(
                itemRepository.save(addStatus(status, currentItem)));
    }

    /**
     * Returns the current status
     * @param postalItemId The postal item ID
     * @return {@link StatusDto}
     */
    @Override
    public StatusDto getCurrentStatus(Long postalItemId) {
        log.info("Method called: getCurrentStatus");
        List<StatusDto> statuses = getPostalItemMovementHistory(postalItemId);
        return statuses.get(statuses.size() - 1);

    }

    /**
     * Returns the entire history of postal item movement
     * @param postalItemId The postal item ID
     * @return list of statuses
     */
    @Override
    public List<StatusDto> getPostalItemMovementHistory(Long postalItemId) {
        log.info("Method called: getPostalItemMovementHistory");
        PostalItem postalItem = getPostalItem(postalItemId);
        return statusMapper.toListStatusDto(postalItem.getMovementHistory());
    }

    /**
     * Find the postal item by ID
     * @param id the postal item ID
     * @return {@link PostalItem}
     */
    @Override
    public PostalItem getPostalItem(Long id) {
        log.info("Method called: getPostalItem");
        return itemRepository.findById(id).orElseThrow(()->{
            PostalItemNotFoundException postalItemNotFound = new PostalItemNotFoundException("The postal item not found");
            log.error("The postal item not found", postalItemNotFound);
            return postalItemNotFound;
        });
    }

    /**
     * Adding the status to the postal item
     * @param status {@link Status} status information
     * @param currentItem {@link PostalItem} the current postal item
     * @return {@link PostalItem}
     */
    private static PostalItem addStatus(Status status, PostalItem currentItem) {
        log.info("Method called: addStatus");
        List<Status> movementHistory = currentItem.getMovementHistory() == null ? new ArrayList<>():currentItem.getMovementHistory();
        movementHistory.add(status);
        currentItem.setMovementHistory(movementHistory);
        return currentItem;
    }
}
