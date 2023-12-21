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

    public static final String JIYUN_DIR = "/jiyun/";
    public static final String JINA_DIR = "/jina/";
    public static final String HYEONKYO_DIR = "/hyeonkyo/";

    private final CashBoxRepository cashBoxRepository;
    private final CoreBankRepository coreBankRepository;
    private final MemoryRepository memoryRepository;

    @Async
    @Transactional
    public void saveDummyData(Long userId) {
        List<Long> coreBankIdList = saveCoreBankData(userId);
        List<Long> cashBoxIdList = saveCashBoxData(userId, coreBankIdList);
        saveMemoryData(cashBoxIdList);
    }

    private List<Long> saveCoreBankData(Long userId) {
        //========= 더미데이터 ============
        String[] accountNums = {"21702448796571", "21702479654744", "21702446796255"};
        int[] bals = {630_000, 1011_000, 520_000};
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
        int[] balances = {630_000, 1011_000, 520_000};
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
        boolean[] checkedMaturityReads = {false, true, false};
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
                    .checkedMaturityRead(checkedMaturityReads[i])
                    .build();
            cashBoxIdList.add(cashBoxRepository.save(cashBox).getId());
        }
        return cashBoxIdList;
    }

    private void saveMemoryData(List<Long> cashBoxIdList) {
        //========= 더미데이터 ============
        String[][] titles = {
                {
                    "지윤이와 공원에서",
                        "크리스마스의 마법",
                        "빛나는 별, 지윤이 첫 학예회",
                        "우리 예쁜 지윤이의 6번째 생일",
                        "지윤이의 두번째 학예회"
                },
                {
                    "우리 진아 벌써 50일!",
                        "오늘도 사랑스러운 우리 진아",
                        "진아 5개월째",
                        "작은 발자국, 큰 행복: 성장하는 우리 진아의 이야기",
                        "1년, 빛나는 진아와 함께한 특별한 시간들",
                        "우리 진아가 좋아하는 그네",
                        "햇살 가득, 모자쓴 진아의 공원 나들이",
                        "신나는 모험, 진아의 장난감 자전거 일일 운전수",
                        "눈 오는 날, 진아와 함께 즐긴 특별한 놀이시간",
                        "진아와 함께한 꽃놀이"
                },
                {
                    "평온한 미소, 현교의 첫 돌 기념일",
                        "현교의 어린이집 일상",
                        "오늘은 현교의 첫 학예회!",
                        "빛나는 6살, 현교의 생일",
                        "설날의 멋쟁이 현교!",
                        "현교의 체육대회~"

                }
        };
        String[][] contents = {
                {
                        "오늘 공원에 와서 그런지 지윤이가 정말 신났다. 지윤이가 신나서 웃는 모습은 역시 너무 귀엽네. 이제 그네도 탈줄 알고 작은 모래시계로도 놀고. 앞으로도 이렇게 자연과 놀이로 가득찬 순간들이 계속되게 엄마가 노력할게. 지윤이의 미소와 행복이 항상 지속되길! \uD83C\uDF08\uD83D\uDE0A",
                        "오늘은 크리스마스 날, 지윤이가 크리스마스 트리를 열심히 꾸미고 있는 모습! 작은 손에는 반짝이는 장식품을 들고, 설렘 가득한 눈빛으로 트리에 장식을 하고 있는게  너무 예쁘네.\n" +
                                "산타할아버지에게 받고 싶은 소원을 빌며 잤는데 어떤 소원일지 궁금하다. 크리스마스 분위기 속에서 지윤이와 함께하는 이 특별한 순간은 영원히 기억될 것 같아. 지윤이의 꿈과 희망이 모두 이뤄지길 기대해. \uD83C\uDF84✨",
                        "지윤이가 오늘은 유치원 학예회에서 토끼옷을 입고 나왔다. 다들 같은 토끼옷을 입었는데도 내눈엔 지윤이밖에 안보이네♥ 지윤이의 활기찬 에너지와 특별한 빛깔이 오늘 따라 더 돋보인다. 우리 지윤이의 빛나는 미소와 춤추는 모습이 학예회를 더욱 특별하게 만들었을 거야. \uD83D\uDC30\uD83D\uDC83✨",
                        "지윤이가 오늘은 6살 생일을 맞아 유치원에서 생일파티를 했어. 특별한 날을 의미있게 보내기 위해 케이크 앞에서 초를 부는 지윤이! 이 순간이 정말 특별하다. 1년에 한 번뿐인 생일, 오늘은 지윤이가 주인공이야. 행복하고 건강한 일년이 되기를 기원해! \uD83C\uDF82\uD83C\uDF89\uD83C\uDF81",
                        "오늘은 지윤이 학예회 날! 예쁜 한복을 입고 열심히 연극하는 지윤이의 모습이 정말 인상적이다. 혹부리 영감에서 주연이 된 멋진 지윤이. 엄마도 자랑스럽네♥ 이 특별한 순간을 기억하면서 지윤이의 예술적인 모습을 응원해. \uD83C\uDFAD\uD83D\uDC4F✨"
                },
                {
                        "우리 진아가 태어난 지 벌써 50일! 자는 시간이 더 많은 것 같지만 바라만 봐도 행복해~ 이 작은 존재가 우리 가정에 더 많은 기쁨과 사랑을 안겨주는 우리 복덩이 진아ㅎㅎ 진아의 평화로운 모습을 바라보면서 매 순간이 정말 소중하고 특별하다는 걸 느낄 수 있어. 앞으로도 진아와 함께하는 모든 순간이 행복으로 가득하길 기원해. \uD83C\uDF19\uD83D\uDCA4\uD83D\uDC96",
                        "엄마 등에서 한 순간도 떨어지지 않으려고 울던 진아였는데, 어느새 혼자서도 잘 앉아있네. 성장하는 모습을 보면서 기쁨과 함께 조금은 아쉬움도 느껴지는 거 같아ㅎㅎ. 그래도 진아가 세상을 탐험하며 자신의 발자취를 남길 모습은 상상하면 기대된. 앞으로 더 많은 순간들이 기다리고 있을 테니, 진아와 함께하는 모든 순간을 소중히 간직해보자. \uD83C\uDF7C\uD83D\uDC76\uD83D\uDC95",
                        "방금 전까지도 신나서 꺄르륵 웃던 진아였는데 피곤했는지 금새 자버렸네. 곤히 잘자는 진아가 너무 귀여워서 많이 찍어버렸다. 이 작은 순간들이 참 소중하고, 진아의 꿈 속에서 무엇을 꾸고 있는지 궁금해지네. 잠에서 깨어나면 또 새로운 모험과 기쁨이 기다리고 있을 텐데, 진아의 세상이 항상 밝고 행복하기를... \uD83C\uDF19\uD83D\uDCA4\uD83D\uDC96",
                        "옛날에는 뒤집기도 힘들어했던 우리 진아, 이제는 곧잘 앉아있네. 앉아있을 때 보이는 조그만한 발이 너무 귀여워♥ 성장하는 모습을 지켜보면서 얼마나 빨리 자릴 찾아가고 있는지 느낄 수 있어 기쁘고 뿌듯하다. 진아의 작은 발자국이 우리 가정에 더 많은 행복을 가져다주길 바라며, 항상 건강하게 자라나길 \uD83D\uDC63\uD83D\uDC95\uD83C\uDF1F",
                        "진아가 벌써 1살이 지났다니 믿기지가 않네. 새 옷도 너무 잘어울리고 예쁘고 귀여워♥ 성장하는 진아의 모습을 보며 시간이 얼마나 빨리 흘렀는지를 느낄 때마다 감회가 새롭다. 우리 진아 오늘도 사랑해~\uD83D\uDC76\uD83D\uDC96",
                        "오늘은 집안에 그네를 설치했어. 진아가 그네를 안좋아할까봐 걱정했는데, 처음에는 낯설어 해도 금새 적응해서 즐겁게 잘 타더라. 내리려고 하면 싫어서 울기까지 할 정도로 맘에 들어해서 정말 다행이야. 우리 진아가 좋아하는 걸 찾아가는 모습을 보는 것만으로도 하루가 행복해지네. 매일매일이 행복만 가득하길~\uD83D\uDE0A✨",
                        "오늘은 우리 진아와 공원 나들이를 갔어. 진아가 먼저 가겠다고 신나서 뛰노는 모습은 정말 항상 귀여워. 그치만 뛰다가 다칠까봐 걱정돼서 엄마의 마음을 두근거리게 한 건 알까? ㅎㅎ 햇빛 때문에 눈부실까봐 모자도 씌웠는데, 잘 착용하고 다니니 다행이야. 모자 쓴 진아는 너무 귀엽다. 공원에서의 이 날은 더없이 즐거운 추억으로 기억될 것 같아. \uD83C\uDF33\uD83D\uDC76\uD83D\uDC95",
                        "오늘은 집에서 하루 종일 장난감 자전거만 탄 진아! 처음에 갖고 싶다고 졸라서 사줬는데, 하루 종일 신나서 타는 모습을 보니 진작에 사줄걸 그랬네. 운전도 잘하는 우리 진아, 아주 베스트 드라이버야!ㅎㅎ 자전거를 타면서 얼마나 즐거워하는지 느낄 수 있어 정말 기쁘다. 앞으로도 좋은 것만 다 사줄게! \uD83D\uDEB4\u200D♀️\uD83D\uDC67\uD83D\uDC96",
                        "눈 오는 날 귀여운 빨간 비니를 쓴 우리 진아! 진아가 눈이 펑펑 온다고 빨리 나가자고 했어. 감기 걸릴까봐 눈이 다 그치면 나가자고 했는데, 싫은 티를 내긴 했지만 꾹 참고 기다려준 우리 진아ㅎㅎ 고마워. 나가자 마자 신나서 뛰어놀고 큰 눈사람도 같이 만들었다. 이 특별한 눈 오는 날은 우리 가족에게 소중한 추억으로 남을 것 같아. ❄️\uD83D\uDC67⛄️",
                        "오늘은 날씨가 너무 좋아서 여러 꽃을 구경하러 공원에 왔어. 멜빵 바지를 입고 뛰어노는 진아는 오늘도 역시 너무 귀엽네~ 이런 활기찬 순간들이 우리 가족에게 행복을 더해줘 \uD83C\uDF38\uD83D\uDC67\uD83D\uDC96"
                },
                {
                        "현교가 벌써 세상에 태어난지 1년이 넘었다니! 돌 기념 사진찍으러 사진관에 갔는데, 어쩜 한 번도 울지 않고 카메라를 잘 응시하다니... 사진 찍는 카메라 아저씨도 안 우는 아이는 처음봤다고 신기해하던 모습이 아직도 생생해. 우리 현교가 건강하게 자라나기를 기대하며, 이 소중한 순간을 기억해두고 싶어. \uD83D\uDCF8\uD83D\uDC76\uD83D\uDC99",
                        "현교가 어린이집에 가서 찍은 사진! 카메라 앞에서도 장난 안치고 잘 웃어서 찍기 편했다고 선생님이 어찌나 칭찬을 하던지. 의젓하게 잘 자라고 있는 것 같아서 엄마는 너무 든든해~ 그치만 말썽부려도 괜찮으니 건강하게만 잘 자라렴~\uD83D\uDE0A\uD83D\uDCF8\uD83D\uDC99",
                        "오늘은 어린이집 학예회 날! 학예회에서 마이클 잭슨이 되어 멋진 춤을 춰 무대에서 빤짝빤짝 빛나는 현교! 엄마 눈에는 빛나는 현교밖에 안보여~ \uD83D\uDE04✨ 이렇게 잘 추는데 나중에 커서 연예인 해도 되겠다! 현교는 앞으로 뭐가 될까~ \uD83C\uDF1F\uD83D\uDC83\uD83D\uDC76",
                        "벌써 현교의 6번째 생일이네. 시간이 너무너무 빠르다. 유치원에서 생일 파티로 현교가 좋아하는 초코 케이크도 먹고 맛있었겠다! \uD83C\uDF82 초를 불때 무슨 소원을 빌었을까? 엄마는 궁금하네ㅎㅎ 현교의 소원이 이루어졌으면 좋겠다~ 앞으로의 날들도 항상 건강하고 행복하기를 기도할게! \uD83C\uDF89\uD83D\uDC66\uD83D\uDC96",
                        "설날 예쁜 한복 입고 오랜만에 친가 내려가서 찍은 사진 한방! 두손 가지런히 모아서 예의 바르게 인사도 잘하고! 역시 우리 기특한 현교야 멋있어!! \uD83C\uDF8A\uD83D\uDC66\uD83C\uDF1F 현교와 함께 행복한 설 명절을 보내서 엄마는 더 행복해~ 앞으로도 현교와 함께하는 모든 순간이 행복하길 바라며, 건강하게 자라나기를 기대해. 새해 복 많이 받아! \uD83C\uDF89\uD83C\uDF87\uD83C\uDF86",
                        "유치원 운동회때 웃고있는 사진 한컷! 예쁜 체육복 입은 현교 모습 한번 찍고 싶었는데 카메라를 갖다대니 씨익 하고 웃는 현교ㅋㅋㅋㅋ 어디서 배운 표정인지 너무 귀엽다 ㅎㅎ 달리기도 열심히 하고 체육대회에서 제일 빛나는건 역시 우리 현교였어!! 맛있는 간식도 많이 먹고 행복한 하루였다~ \uD83C\uDFC3\u200D♂️\uD83D\uDC66\uD83C\uDF1F 현교의 밝은 에너지로 운동회가 더욱 활기차 보였던 것 같아. 앞으로도 건강하고 즐거운 순간들이 많아지길! \uD83D\uDE0A\uD83C\uDF89✨"
                }
        };
        int[][] depositAmounts = {
                {
                    120_000, 50_000, 80_000, 180_000, 200_000
                },
                {
                    120_000, 130_000, 55_000, 78_000, 150_000, 170_000, 95_000, 45_000, 78_000, 90_000
                },
                {
                    150_000, 60_000, 70_000, 120_000, 70_000, 50_000,
                }
        };
        List[][] images = {
                {
                        List.of(JIYUN_DIR.concat("jy1.png"), JIYUN_DIR.concat("jy2.png"), JIYUN_DIR.concat("jy3.png")),
                        List.of(JIYUN_DIR.concat("jy4.png")),
                        List.of(JIYUN_DIR.concat("jy5.png"), JIYUN_DIR.concat("jy6.png")),
                        List.of(JIYUN_DIR.concat("jy7.png"), JIYUN_DIR.concat("jy8.png")),
                        List.of(JIYUN_DIR.concat("jy9.png"), JIYUN_DIR.concat("jy10.png"))
                },
                {
                        List.of(JINA_DIR.concat("jina1.png"), JINA_DIR.concat("jina2.png")),
                        List.of(JINA_DIR.concat("jina3.png")),
                        List.of(JINA_DIR.concat("jina4.png")),
                        List.of(JINA_DIR.concat("jina5.png")),
                        List.of(JINA_DIR.concat("jina6.png")),
                        List.of(JINA_DIR.concat("jina7.png")),
                        List.of(JINA_DIR.concat("jina8.png")),
                        List.of(JINA_DIR.concat("jina9.png")),
                        List.of(JINA_DIR.concat("jina10.png")),
                        List.of(JINA_DIR.concat("jina11.png"))
                },
                {
                        List.of(HYEONKYO_DIR.concat("hk1.png"), HYEONKYO_DIR.concat("hk2.png")),
                        List.of(HYEONKYO_DIR.concat("hk3.png")),
                        List.of(HYEONKYO_DIR.concat("hk4.png")),
                        List.of(HYEONKYO_DIR.concat("hk5.png")),
                        List.of(HYEONKYO_DIR.concat("hk6.png")),
                        List.of(HYEONKYO_DIR.concat("hk7.png"))
                }
        };

        //========= 저장 ============
        for (int i = 0; i < N_CASH_BOX; i++) {
            for (int j = 0; j < titles[i].length; j++) {
                Memory memory = Memory.builder()
                        .cashBoxId(cashBoxIdList.get(i))
                        .title(titles[i][j])
                        .content(contents[i][j])
                        .depositAmount(depositAmounts[i][j])
                        .images(images[i][j])
                        .build();
                memoryRepository.save(memory);
            }
        }
    }

}
