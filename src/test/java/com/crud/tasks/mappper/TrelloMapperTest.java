package com.crud.tasks.mappper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapTrelloCardDtoToTrelloCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("testName", "testDescription", "testPos", "testListId");
        //Then
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //When
        Assert.assertEquals(trelloCardDto.getName(), trelloCard.getName());
        Assert.assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        Assert.assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        Assert.assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }

    @Test
    public void mapTrelloCardToTrelloCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("testName", "testDescription", "testPos", "testListId");
        //Then
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //When
        Assert.assertEquals(trelloCardDto.getName(), trelloCard.getName());
        Assert.assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        Assert.assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        Assert.assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }

    @Test
    public void mapTrelloListToTrelloListDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "testList1", false);
        TrelloList trelloList2 = new TrelloList("2", "testList2", false);
        TrelloList trelloList3 = new TrelloList("3", "testList3", false);
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(trelloList1);
        trelloList.add(trelloList2);
        trelloList.add(trelloList3);
        //Then
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);
        //When
        Assert.assertEquals(trelloList.size(), 3);
        Assert.assertEquals(trelloList.get(1).getId(), trelloListDto.get(1).getId());
        Assert.assertEquals(trelloList.get(1).getName(), trelloListDto.get(1).getName());
        Assert.assertEquals(trelloList.get(1).isClosed(), trelloListDto.get(1).isClosed());
    }

    @Test
    public void mapTrelloListDtoToTrelloListTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "testList1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "testList2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "testList3", false);
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(trelloListDto1);
        trelloListDto.add(trelloListDto2);
        trelloListDto.add(trelloListDto3);
        //Then
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDto);
        //When
        Assert.assertEquals(trelloListDto.size(), 3);
        Assert.assertEquals(trelloListDto.get(1).getId(), trelloList.get(1).getId());
        Assert.assertEquals(trelloListDto.get(1).getName(), trelloList.get(1).getName());
        Assert.assertEquals(trelloListDto.get(1).isClosed(), trelloList.get(1).isClosed());
    }

    @Test
    public void mapTrelloBoardToTrelloBoardDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "testList1", false);
        TrelloList trelloList2 = new TrelloList("2", "testList2", false);
        TrelloList trelloList3 = new TrelloList("3", "testList3", false);
        List<TrelloList> trelloListA = new ArrayList<>();
        trelloListA.add(trelloList1);
        trelloListA.add(trelloList2);
        trelloListA.add(trelloList3);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board1", trelloListA);

        TrelloList trelloList4 = new TrelloList("1", "testList1", false);
        TrelloList trelloList5 = new TrelloList("2", "testList2", false);
        TrelloList trelloList6 = new TrelloList("3", "testList3", false);
        TrelloList trelloList7 = new TrelloList("4", "testList4", true);
        List<TrelloList> trelloListB = new ArrayList<>();
        trelloListB.add(trelloList4);
        trelloListB.add(trelloList5);
        trelloListB.add(trelloList6);
        trelloListB.add(trelloList7);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "board2", trelloListB);

        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard1, trelloBoard2);

        //Then
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoardList);
        TrelloBoardDto trelloBoardDto1 = trelloBoardDto.get(0);
        TrelloBoardDto trelloBoardDto2 = trelloBoardDto.get(1);
        int countOfBoards = trelloBoardDto.size();
        int countOfBoardsTrelloBoard1 = trelloBoardDto1.getLists().size();
        int countOfBoardsTrelloBoard2 = trelloBoardDto2.getLists().size();
        //When
        Assert.assertEquals(2, countOfBoards);
        Assert.assertEquals(countOfBoardsTrelloBoard1, 3);
        Assert.assertEquals(countOfBoardsTrelloBoard2, 4);
        Assert.assertEquals("testList2", trelloBoardDto1.getLists().get(1).getName());
        Assert.assertEquals(true, trelloBoardDto2.getLists().get(3).isClosed());
    }

    @Test
    public void mapToTrelloBoardDtoToTrelloBoard() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "testList1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "testList2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "testList3", false);
        List<TrelloListDto> trelloListDtoA = new ArrayList<>();
        trelloListDtoA.add(trelloListDto1);
        trelloListDtoA.add(trelloListDto2);
        trelloListDtoA.add(trelloListDto3);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "board1", trelloListDtoA);

        TrelloListDto trelloListDto4 = new TrelloListDto("1", "testList1", false);
        TrelloListDto trelloListDto5 = new TrelloListDto("2", "testList2", false);
        TrelloListDto trelloListDto6 = new TrelloListDto("3", "testList3", false);
        TrelloListDto trelloListDto7 = new TrelloListDto("4", "testList4", true);
        List<TrelloListDto> trelloListDtoB = new ArrayList<>();
        trelloListDtoB.add(trelloListDto4);
        trelloListDtoB.add(trelloListDto5);
        trelloListDtoB.add(trelloListDto6);
        trelloListDtoB.add(trelloListDto7);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "board2", trelloListDtoB);

        List<TrelloBoardDto> trelloBoardDtoList = Arrays.asList(trelloBoardDto1, trelloBoardDto2);

        //Then
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardDtoList);
        TrelloBoard trelloBoard1 = trelloBoard.get(0);
        TrelloBoard trelloBoard2 = trelloBoard.get(1);
        int countOfBoards = trelloBoard.size();
        int countOfBoardsTrelloBoard1 = trelloBoard1.getLists().size();
        int countOfBoardsTrelloBoard2 = trelloBoard2.getLists().size();
        //When
        Assert.assertEquals(2, countOfBoards);
        Assert.assertEquals(countOfBoardsTrelloBoard1, 3);
        Assert.assertEquals(countOfBoardsTrelloBoard2, 4);
        Assert.assertEquals("testList2", trelloBoard1.getLists().get(1).getName());
        Assert.assertEquals(true, trelloBoard2.getLists().get(3).isClosed());
    }

}