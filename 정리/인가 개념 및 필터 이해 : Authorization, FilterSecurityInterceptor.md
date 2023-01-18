
# Authorization

- 당신에게 무엇이 허가 되었는지 증명하는 것

Spring Security는 2가지 영역으로 구성되어있음

1. Authentication
    - 사용자가 어떤 Web Application에 접근을 하려고 할 때 인증을 받았는 지 안받았는지에 대한 여부를 판단하게 됨
2. Authorization
    - 인증을 받은 사용자가 어떤 자원에 접근하고자할때 사용자의 권한에 충분한 자격을 갖추고있는지에 대한 판단

## 스프링 시큐리티가 지원하는 권한 계층

1. 웹 계층
    - URL 요청에 따른 메뉴 혹은 화면단위의 레벨 보안
    - e.g) /user 에 설정된 권한과 사용자가 가진 권한을 비교하여 인가처리
2. 서비스 계층
    - 화면 단위가 아닌 메소드 같은 기능 단위의 레벨 보안
    - e.g) user() 메소드에 설정된 권한과 사용자가 가진 권한과 서로 일치하는지 판단하여 인가처리
3. 도메인 계층(Access Control List, 접근제어목록)
    - 객체 단위의 레벨 보안
    - e.g) user 도메인에 설정된 권한과 사용자가 가진 권한과 서로 일치하는지 판단하여 인가처리

# FilterSecurityIntercptor

- 마지막에 위치한 필터로써 인증된 사용자에 대하여 특정 요청의 승인/거부 여부를권한정보
- 인증객체없이 보호자원에 접근을 시도할 경우 AuthenticationException을 발생
- 인증 후 자원에 접근 가능한 권한이 존재하지 않을 경우 AccessDeniedException을 발생
- 권한 제어 방식 중 HTTP 자원의 보안을 처리하는 필터
- 권한 처리를 AccessDecisionManager 에게 맡김

<img width="1031" alt="image" src="https://user-images.githubusercontent.com/46226445/213156173-85295b90-5970-4543-b6c9-b0e3cc22a9d3.png">

1. **사용자가 가지고 있는 인증객체의 존재 여부 체크**
    1. 없다면 → AutjenticationException → ExceptionTranslationFilter 
    2. 있다면 → SecurityMetadataSource (사용자가 요청한 자원에 필요한 권한 정보 조회해서 전달)
   
2. **권한정보의 존재 여부**
    1. null 일 경우 권한 심사 하지 않음 → 모든 자원 접근 허용 
    2. 존재한다면 AccessDecisionManager (최종 심의 결정자) 에게 전달
    3. 심의 요청 AccessDecisionVoter (심의자)가 
        1. 승인
        2. 거부
        
3. **접근 승인**
    1. 승인: 자원 접근 허용
    2. 거부: AccessDeniedException → ExceptionTranslationFilter
