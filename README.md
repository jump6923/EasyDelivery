﻿# 🛵EasyDelivery (12.05 ~ 12.11)
## 팀 소개
| <img src ="https://avatars.githubusercontent.com/u/146907732?v=4" width="200px" height="200px"> | <img src ="https://avatars.githubusercontent.com/u/130152696?v=4" width="200px" height="200px"> | <img src ="https://avatars.githubusercontent.com/u/99391320?v=4" width="200px" height="200px"> | <img src ="https://avatars.githubusercontent.com/u/126138030?v=4" width="200px" height="200px"> |
|:-----------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------:|:------------------------------------------------------------------:|:-------------------------------------------------------------------:|
|                                               팀장                                                |                                               팀원                                                |                                 팀원                                 |                                 팀원                                  |
|                         김진환<br>[@jump6923](https://github.com/jump6923)                         |                          길경남<br>[@GyungKu](https://github.com/GyungKu)                          |          이예진<br>[@dlwls423](https://github.com/dlwls423)           |            안태인<br>[@TAENNOS](https://github.com/TAENNOS)            |
|                               -사용자 도메인<br>-인증,인가<br>-백오피스: 회원관리기능                               |                        -장바구니 도메인<br>-리뷰 도메인<br>-소셜 로그인(OAuth): 카카오, 네이버                         |                          -주문 도메인<br>-예외처리                          |                        -상품 도메인<br>-상품 데이터 세팅                        |

## 프로젝트 기능 및 설계
### [회원가입 & 로그인]
🔎 회원가입
- 자체 회원가입
- 소셜 로그인(OAuth): 카카오, 네이버

🔎 로그인(JWT)
- JWT로 보안성 보장

### [회원 관련 기능]
🔎 공통 기능
- 패스워드 변경
- 최근 3번 이내 사용한 비밀번호 사용 불가
- 프로필 조회/수정

🔎 관리자 기능
- 회원 개별/목록 조회
- 회원 차단/해제
- 운영자 권한 부여

### [상품]
🔎 공통 기능
- 상품 상세/목록 조회 구현

🔎 관리자 기능
- 상품 추가/수정/삭제 구현

### [장바구니]
🔎 사용자 기능
- 상품 담기
- 상품 수량 수정
- 전체목록/개별상품 삭제
- 상품 목록 조회


### [주문]
🔎 관리자 기능
- 관리자는 생성 불가
- 관리자는 모든 주문 조회/상태변경/삭제
🔎 사용자 기능
- 장바구니 목록 주문
- 주문 조회/삭제

### [리뷰]
🔎 관리자 기능
- 관리자는 생성/수정 불가

🔎 사용자 기능
- 주문건에 한해 리뷰 추가/수정/삭제

### [백오피스]
🔎 상기 관리자 기능
- 매출 관리

### ERD
<img src="https://teamsparta.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F83c75a39-3aba-4ba4-a792-7aefe4b07895%2Fcc3ed4d6-5a36-42e2-bd3a-3efc98fe9e18%2F%25EC%258B%25AC%25ED%2599%2594_%25ED%258C%2580%25EA%25B3%25BC%25EC%25A0%259C_ERD.png?table=block&id=8f839851-b43e-4f62-9e7c-df6211ca7895&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=1740&userId=&cache=v2">

## Commit 전략
`태그 : 제목` 의 형태이며, `:`뒤에만 space가 있음에 유의한다.

|    태그    |              내용               |
|:--------:|:-----------------------------:|
|   feat   |           새로운 기능 추가           |
|   fix    |             버그 수정             |
|   docs   |             문서 수정             |
|  style   | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| refactor |            코드 리펙토링            |
|   test   |    테스트 코드, 리펙토링 테스트 코드 추가     |
|  chore   |     빌드 업무 수정, 패키지 매니저 수정      |

## Branch 전략
|      태그      |      내용       |
|:------------:|:-------------:|
|     main     |  서비스 운영 브랜치   |
|     dev      |   개발중인 브랜치    |
|  feature/이름  | 팀원별 기능 개발 브랜치 |

## Tech Stack
- Programming: <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white"/>
- Framework:  <img src="https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white"/>
- Database: <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
- security: <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>
