package com.memorybox.service;

import com.memorybox.domain.cashbox.entity.CashBox;
import com.memorybox.domain.cashbox.repository.CashBoxRepository;
import com.memorybox.domain.corebank.entity.CoreBank;
import com.memorybox.domain.corebank.repository.CoreBankRepository;
import com.memorybox.domain.memory.entity.Memory;
import com.memorybox.domain.memory.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DummyDataService {

    public static final int N_CASH_BOX = 3;
    public static final int N_CORE_BANK = 3;
    public static final int N_MEMORY = 3;

    private final CashBoxRepository cashBoxRepository;
    private final CoreBankRepository coreBankRepository;
    private final MemoryRepository memoryRepository;

    @Async
    @Transactional
    public void saveDummyData(Long userId) {
        //TODO 저금통 3개 생성 + 계좌 3개 생성, 각 저금통에 추억 3개씩 넣기
        List<Long> coreBankIdList = saveCoreBankData(userId);
        List<Long> cashBoxIdList = saveCashBoxData(userId, coreBankIdList);
        saveMemoryData(cashBoxIdList);
    }

    private List<Long> saveCoreBankData(Long userId) {
        //========= 더미데이터 ============
        String[] accountNums = {"21702448796571", "21702479654744", "21702446796255"};
        int[] bals = {6834000, 535000, 8865767};
        String[] productNames = {"특별한 적금", "특별한 적금", "특별한 적금"};
        LocalDate[] startDates = {
                LocalDate.of(2023, 1, 22),
                LocalDate.of(2021, 12, 27),
                LocalDate.of(2022, 5, 16)};
        LocalDate[] maturityDates = {
                LocalDate.of(2024, 5, 4),
                LocalDate.of(2023, 1, 22),
                LocalDate.of(2024, 6, 21),};

        //========= 저장 ============
        List<Long> coreBankIdList = new ArrayList<>();
        for (int i = 0; i < N_CORE_BANK; i++) {
            CoreBank coreBank = CoreBank.builder()
                    .userId(userId)
                    .accountNum(accountNums[i])
                    .balance(bals[i])
                    .productName(productNames[i])
                    .startDate(startDates[i])
                    .maturityDate(maturityDates[i])
                    .build();
            coreBankIdList.add(coreBankRepository.save(coreBank).getId());
        }
        return coreBankIdList;
    }

    private List<Long> saveCashBoxData(Long userId, List<Long> coreBankIdList) {
        //========= 더미데이터 ============
        String[] names = {"우리 지윤-어린이집", "우리 진아-유치원", "우리현교 - 어린이집"};
        String[] descriptions = {"지윤이 성장일지", "귀여운 진아 유치원때", "우리현교"};
        String[] thumbnails = {
                "https://robohash.org/nonetet.png?size=50x50&set=set1",
                "https://robohash.org/ullamvoluptatesnihil.png?size=50x50&set=set1",
                "https://robohash.org/minimaquiaaut.png?size=50x50&set=set1"};
        String[] accountNums = {"21702448796571", "21702479654744", "21702446796255"};
        int[] balances = {6834000, 535000, 8865767};
        String[] productNames = {"특별한 적금", "특별한 적금", "특별한 적금"};
        LocalDate[] startDates = {
                LocalDate.of(2023, 1, 22),
                LocalDate.of(2021, 12, 27),
                LocalDate.of(2022, 5, 16)};
        boolean[] maturityEnableds = {false, true, false};
        LocalDate[] maturityDates = {
                LocalDate.of(2024, 5, 4),
                LocalDate.of(2023, 1, 22),
                LocalDate.of(2024, 6, 21),};

        //========= 저장 ============
        List<Long> cashBoxIdList = new ArrayList<>();
        for (int i = 0; i < N_CASH_BOX; i++) {
            CashBox cashBox = CashBox.builder()
                    .userId(userId)
                    .name(names[i])
                    .description(descriptions[i])
                    .thumbnail(thumbnails[i])
                    .accountNum(accountNums[i])
                    .balance(balances[i])
                    .productName(productNames[i])
                    .coreBankId(coreBankIdList.get(i))
                    .startDate(startDates[i])
                    .maturityEnabled(maturityEnableds[i])
                    .maturityDate(maturityDates[i])
                    .build();
            cashBoxIdList.add(cashBoxRepository.save(cashBox).getId());
        }
        return cashBoxIdList;
    }

    private void saveMemoryData(List<Long> cashBoxIdList) {
        //========= 더미데이터 ============
        String[] titles = {};
        String[] contents = {
                "오늘 공원에 와서 그런지 지윤이가 정말 신났다. 지윤이가 신나서 웃는 모습은 역시 너무 귀엽네. 이제 그네도 탈줄 알고 작은 모래시계로도 놀고. 앞으로도 이렇게 자연과 놀이로 가득찬 순간들이 계속되게 엄마가 노력할게. 지윤이의 미소와 행복이 항상 지속되길! \uD83C\uDF08\uD83D\uDE0A",
        "오늘은 크리스마스 날, 지윤이가 크리스마스 트리를 열심히 꾸미고 있는 모습! 작은 손에는 반짝이는 장식품을 들고, 설렘 가득한 눈빛으로 트리에 장식을 하고 있는게  너무 예쁘네.\n" +
                "산타할아버지에게 받고 싶은 소원을 빌며 잤는데 어떤 소원일지 궁금하다. 크리스마스 분위기 속에서 지윤이와 함께하는 이 특별한 순간은 영원히 기억될 것 같아. 지윤이의 꿈과 희망이 모두 이뤄지길 기대해. \uD83C\uDF84✨",
        "지윤이가 오늘은 유치원 학예회에서 토끼옷을 입고 나왔다. 다들 같은 토끼옷을 입었는데도 내눈엔 지윤이밖에 안보이네♥ 지윤이의 활기찬 에너지와 특별한 빛깔이 오늘 따라 더 돋보인다. 우리 지윤이의 빛나는 미소와 춤추는 모습이 학예회를 더욱 특별하게 만들었을 거야. \uD83D\uDC30\uD83D\uDC83✨",
        "지윤이가 오늘은 6살 생일을 맞아 유치원에서 생일파티를 했어. 특별한 날을 의미있게 보내기 위해 케이크 앞에서 초를 부는 지윤이! 이 순간이 정말 특별하다. 1년에 한 번뿐인 생일, 오늘은 지윤이가 주인공이야. 행복하고 건강한 일년이 되기를 기원해! \uD83C\uDF82\uD83C\uDF89\uD83C\uDF81",
        "오늘은 지윤이 학예회 날! 예쁜 한복을 입고 열심히 연극하는 지윤이의 모습이 정말 인상적이다. 혹부리 영감에서 주연이 된 멋진 지윤이. 엄마도 자랑스럽네♥ 이 특별한 순간을 기억하면서 지윤이의 예술적인 모습을 응원해. \uD83C\uDFAD\uD83D\uDC4F✨",
        "우리 진아가 태어난 지 벌써 50일! 자는 시간이 더 많은 것 같지만 바라만 봐도 행복해~ 이 작은 존재가 우리 가정에 더 많은 기쁨과 사랑을 안겨주는 우리 복덩이 진아ㅎㅎ 진아의 평화로운 모습을 바라보면서 매 순간이 정말 소중하고 특별하다는 걸 느낄 수 있어. 앞으로도 진아와 함께하는 모든 순간이 행복으로 가득하길 기원해. \uD83C\uDF19\uD83D\uDCA4\uD83D\uDC96",
        "방금 전까지도 신나서 꺄르륵 웃던 진아였는데 피곤했는지 금새 자버렸네. 곤히 잘자는 진아가 너무 귀여워서 많이 찍어버렸다. 이 작은 순간들이 참 소중하고, 진아의 꿈 속에서 무엇을 꾸고 있는지 궁금해지네. 잠에서 깨어나면 또 새로운 모험과 기쁨이 기다리고 있을 텐데, 진아의 세상이 항상 밝고 행복하기를 기원해. \uD83C\uDF19\uD83D\uDCA4\uD83D\uDC96",
        "엄마 등에서 한 순간도 떨어지지 않으려고 울던 진아였는데, 어느새 혼자서도 잘 앉아있네. 성장하는 모습을 보면서 기쁨과 함께 조금은 아쉬움도 느껴지는 거 같아ㅎㅎ. 그래도 진아가 세상을 탐험하며 자신의 발자취를 남기는 모습은 정말 놀라워. 앞으로 더 많은 순간들이 기다리고 있을 테니, 진아와 함께하는 모든 순간을 소중히 간직해보자. \uD83C\uDF7C\uD83D\uDC76\uD83D\uDC95",
        "옛날에는 뒤집기도 힘들어했던 우리 진아, 이제는 곧잘 앉아있네. 앉아있을 때 보이는 조그만한 발이 너무 귀여워♥ 성장하는 모습을 지켜보면서 얼마나 빨리 자릴 찾아가고 있는지 느낄 수 있어 기쁘고 뿌듯하다. 진아의 작은 발자국이 우리 가정에 더 많은 행복을 가져다주길 바라며, 항상 건강하게 자라나길 \uD83D\uDC63\uD83D\uDC95\uD83C\uDF1F"};
        int[] depositAmounts = {};
        List[] images = {List.of("1"), List.of("2")};

        //========= 저장 ============
        for (int i = 0; i < N_CASH_BOX; i++) {
            for (int j = 0; j < N_MEMORY; j++) {
                int index = i * N_MEMORY + j;
                Memory memory = Memory.builder()
                        .title(titles[index])
                        .content(contents[index])
                        .depositAmount(depositAmounts[index])
                        .images(images[index])
                        .build();
                memoryRepository.save(memory);
            }
        }
    }

}
