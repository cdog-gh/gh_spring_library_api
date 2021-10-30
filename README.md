# Spring boot library API
도서관 관련 api를 spring boot로 구현한 프로젝트입니다.

### Feature
+ JWT token 기반 login 기능 제공
+ 관리자로 로그인 하였을 때 도서 삽입, 삭제 기능 제공

### TODO
- [x] postgresql 연동
- [x] swagger 연동 및 authorization 관련 처리
- [x] 도서 생성, 삭제, 조회 API
- [x] 회원 가입, 로그인 API
- [x] 빌린 책 목록 보는 API에서 user 인가 처리
- [x] packaging 해서 해당 프로젝트를 서버에 구축하기 편하게끔 컨테이너화
- [ ] 도서 대출 관련 제약 추가 (ex. 연체)
- [ ] 회원 가입 후 메일 인증 시스템

## Setup
- Dockerfile이 있는 디렉토리에서 docker compose up -d 명령어를 수행합니다.

## 초기 Setting 값
- ROLE_ADMIN 계정인 user_name은 "cho"이고, user_pw는 "chogahui"입니다.

