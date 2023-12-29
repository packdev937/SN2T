## 어플리케이션의 목표 
> 노션에서 티스토리로 쉽게 포스팅을 전송할 수 있게 하자

## Flow Chart
![flow-chart](flowchart.png)

## 기능 구현 목록
- [ ] Notion에 데이터베이스를 생성한다
  - [ ] 생성 과정을 설명해주는 웹 사이트를 제작한다  
    - [ ] 데이터 베이스는 full page view로 설정해야 한다
    - [ ] Add Connection에 Notion API 계정을 추가한다 
- [ ] Tistory를 연동한다 
  - [ ] Tistory APP ID, Secret Key 생성 과정을 설명해주는 웹 사이트를 제적한다 
  - [ ] 두 키를 사용하여 Authorization Key와 Access Token을 자동으로 발급 받는다
- [ ] 버튼을 누르면 현재 작성된 게시글을 티스토리로 전송한다
  - [ ] 자동으로 변경을 감지하고 티스토리로 전송한다 
- [ ] 회원 가입을 한다
- [ ] 로그인을 한다
  - [ ] 로그인 시 회원 정보들을 config 파일에 적용한다
- [ ] Notion Secret Key를 저장한다 
- [ ] Notion Database Id를 저장한다
- [ ] 연동이 잘 되었는지 확인한다
  - [ ] Database Query를 날려 응답이 온다면 정상적인 수행으로 간주한다 


## Reference
- **Tistory API** 
  - https://tistory.github.io/document-tistory-apis/apis/
- **Notion API**
  - https://developers.notion.com/
  - https://www.notion.so/my-integrations