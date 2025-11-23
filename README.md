<div align="center">

<!-- logo -->
<img src="https://github.com/user-attachments/assets/ee2457c6-609b-4d4e-9e9b-4786146e3b5d" width="400"/>

### Studyspot 프로젝트 Back-end Git Reamd.me

프로젝트 기간 : 2025.11.04 ~ 2025.11.25

</div> 

## 📝 프로젝트 소개
### 🔍 StudySpot  나에게 맞는 공부 공간 찾기 서비스
### 기획 동기
공부를 하다 보면 ‘오늘은 집중 잘 되는 카페 없을까?’, ‘노트북 써도 되는 독서실 어디 있지?’ 같은 고민을 자주 했습니다.

특히 개발 공부를 하면서 장소에 따라 몰입도가 크게 달라지는 걸 느꼈고,
“그날의 기분이나 목적에 맞게 최적의 공부 장소를 추천해주는 서비스가 있으면 좋겠다”는 생각에서 나온 아이디어입니다.

### MVP

• 사용자가 “공부하고 싶은 장소 분위기”를 입력한다.  
(예: “카페”, “조용한 곳”, “노트북 사용 가능”)

• 사용자의 조건에 맞는 장소 리스트를 추천한다.  
(예: 서울 내 조건에 맞는 카페나 독서실 목록)

• 추천된 장소에 대한 기본 정보 제공  
(주소, 영업시간, 콘센트 유무, 평균 소음도, 메뉴 가격대 등)

• 간단한 “오늘 공부 타이머” 기능 추가  
(예: 50분 공부 + 10분 휴식 / 남은 시간 표시)

## 👩‍🎨 프로젝트 화면 구성

- 프로토타입은 크게 검색창, 타이머창으로 구성.
- 각각에 대한 플로우 영상 (Figma 기반 프로토타입 영상)

### 🔍 검색창
|                                                  AI 검색창                                                  |                                                       선택형 검색창                                                        |
|:--------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------:|
| <img src="https://github.com/user-attachments/assets/16e994c0-8458-4f68-b3d1-d925b69c4b74" width="400"/> |        <img src="https://github.com/user-attachments/assets/231ba4ac-32cb-4583-9989-9f03b61ad391" width="400"/>        |


### 🕣 타이머창
|                                                            타이머 실행 창                                                            |                                                 타이머 기록 창                                                 |
|:------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------:|
| <img src="https://github.com/user-attachments/assets/6f86d19a-3c8b-4b46-9a77-d40b665b735c" width="400"/> | <img src="https://github.com/user-attachments/assets/5a18d21d-69bb-4e02-9af7-f8132192d3f7" width="400"/> |


## 📃 프로젝트 API 설계

[API 설계 보러가기](https://www.notion.so/myknow/2025-11-07-API-CRUD-2a4406c3c48880148044d2c951ddb490?source=copy_link)


## 📚 ERD 설계

[ERD 설계 보러가기](https://dbdiagram.io/d/690ee28d6735e11170d2bbaf)

<img src="https://github.com/user-attachments/assets/a3a442d8-2b48-4026-bb2f-e8a8609bae24" width="400"/>


## 🛠️ 프로젝트 아키텍쳐 설계
```
domain
├─ common
│
├─ user
│  ├─ controller
│  ├─ service
│  ├─ dto
│  ├─ domain
│  │  ├─ model      
│  │  └─ vo         
│  ├─ repository
│  └─ exception
│
├─ timer
│  ├─ controller
│  ├─ service
│  ├─ dto
│  ├─ domain
│  │  ├─ model
│  │  └─ vo
│  ├─ repository
│  └─ exception
│
├─ cafe
│  ├─ controller
│  ├─ service
│  ├─ dto
│  ├─ domain
│  │  ├─ model
│  │  └─ vo
│  ├─ repository
│  └─ exception
│
└─ review
   ├─ controller
   ├─ service
   ├─ dto
   ├─ domain
   │  ├─ model
   │  └─ vo
   ├─ repository
   └─ exception

```


## ⚙ 기술 스택

### Back-end
- SpringBoot
- JPA
- PostgreSQL
- JWT
- java
- QueryDSL

### Infra
- Nginx 
- Docker compose 
- Github Action 
- Grafana 
- cAdvisor 
- PostgreSQL Exporter 
- Node Exporter 
- PGAdmin 
- Hoppscotch 
- MiniO
- Prometheus
- Loki
- promtail

### Tools
- Github
- Notion
- Discord
- Figma

## 🙋‍♀️리뷰 및 논의 내용

[리뷰 및 논의 내용 보러가기](https://www.notion.so/myknow/2b4406c3c488807e8fa0e7b5d99220b0?source=copy_link)


## 💁‍♂️ Back-end & Infra 담당 팀원
|             Backend 팀장             |            DevOps 팀장             |         UX/UI 팀장 / Backend 팀원          |
|:----------------------------------:|:--------------------------------:|:--------------------------------------:|
|              [정우재](https://github.com/Woojae-Jeong)               | [정민호](https://github.com/MyKnow) | [박다빈](https://github.com/parking-been) |


### README.md 템플릿 출처
https://github.com/yewon-Noh/readme-template/blob/main/backend/README.md