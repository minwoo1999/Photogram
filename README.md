![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/29de656a-8ab1-4d52-be67-d40d9de31b62/Untitled.png)

### **🔍 프로젝트 기간**

- **2023.01. ~ 2023.02.**

## **💡 Background**

- **김영한선생님의 실전! 스프링 부트와 JPA 활용2 - API 개발과 성능 최적화를 수강하고 실전 프로젝트를 진행하였으며, 성능최적화를 적용해보고자 진행**
- **평소 Oauth2 소셜 로그인에 관심을 보였고, 직접 구현을 해보고 싶었습니다.**

## **🔍 사용한 기술 및 라이브러리**

- **BackEndTool: Spring DATA JPA**
- **DB : MariaDb**
- **Auth: Session, Oauth2(facebook social login)**
- **AspectJ: AOP**

### **DATABASE Schema**

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7986ce95-8870-4f57-9dc3-6c171d17ee60/Untitled.png)

## **💻 리팩토링 및 배운핵심기술**

- **Auth를 이용하여 Session 로그인 구현**
- **Oatuh2 client 를 사용하여 social login(facebook)을 구현**
- **성능 최적화진행(N+1)문제 해결**
- **AOP를활용하여 전처리 과정에서 Validation 공통로직 처리, 후처리과정에서 로그처리**

## **이 프로젝트를 통해 알게된 점**

- **N+1 과 같은문제를 해결할 수 있게 되었습니다.**
- **Mysql 뿐만 아니라 MariaDb도 사용할수 있게 되었습니다.**
- **Auth 로그인의 원리를 이용하고, Oauth2 client를 사용하여 facebook 소셜로그인을 구현해보았습니다.**
- **AOP를 사용함으로써 전처리와 후처리를 통해 Validation 체크 및 로그처리를 공통기능과 핵심기능을 분리하여 구현해보았습니다.**
- **open-in-view를 False로 돌리게되면 트랜잭션을 종료할 때 영속성 컨텍스트를 닫고, 데이터베이스 커넥션도 반환한다.그러므로 커넥션 리소스를 낭비하지 않는다. OSIV를 끄면 모든 지연로딩을 트랜잭션 안에서 처리해야 한다 → 지연 로딩 코드를 트랜잭션 안으로 넣어야 한다. (서비스와 컨트롤러의 명확한 분리를 필요로 한다.)**
