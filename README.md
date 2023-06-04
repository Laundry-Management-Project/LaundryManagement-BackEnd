
# 세탁나라 (세탁소 장부 관리 프로젝트)

> 수기로 작성하는 세탁소 장부를 앱으로 만들어서 세탁소 사장님들의 고충을 덜어주고 싶어 시작한 프로젝트입니다.

>사장님은 손님들의 예약 정보를 확인할 수 있고 손님은 원하는 세탁소에 예약할 수 있도록 만들었습니다.
> 또한 세탁소에 세탁을 완료한 손님 정보를 파악하여 해당 일별 수입 합계와 월간 수입 합계같은 통계 데이터를 조회할 수 있도록 만들었습니다.

## 기술스택
- Java 11
- Spring boot, Spring Data Jpa
- MariaDB
- AWS RDS (클라우드 RDB)
- koyeb (PaaS)
- Swagger (Rest API 문서화)
- Junit5 (테스트)


### 프로젝트 관리
- Notion 
  - https://uncovered-narcissus-6c4.notion.site/Laundry-Management-Project-dd55db33d45c45739d11b81f4a8e222d
- Figma(미완성)
  - https://www.figma.com/file/6PB4jbroiI7WXGBDYciCVd/Untitled?node-id=0%3A1&t=IXcFJxTJr1FCTlOt-1

### 주요 기능
- 사장
  - 사장님은 세탁소를 등록 후 해당 세탁소에 예약되어 있는 세탁물 조회
  - 예약 정보를 기반으로 세탁이 완료된 손님의 정보를 파악하여 일별/월별 수입과 당월, 당년 수입 합계 통계를 보여줌
- 손님
  - 본인이 예약한 예약 정보를 보여줌.
  - 원하는 세탁를 찾아 예약

<br>

> 현재 웹에서 진행하던 프로젝트를 안드로이드로 변경하여 진행 중 입니다. 아래 링크는 웹으로 개발한 코드를 보실 수 있습니다.<br>
> https://github.com/Yu-YoungWoo/LaundryManagement

### 메인화면
<img src="/IMG/home.png"  width="700" height="370">

### 손님 목록
<img src="/IMG/customerList.png"  width="700" height="370">

### 수입 통계
<img src="/IMG/revenue.png"  width="700" height="370">
