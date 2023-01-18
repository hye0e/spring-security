# AccessDecisionManager

- 인증 정보, 요청 정보, 권한 정보를 이용하여 사용자의 자원접근을 허용할 것인지 거부할 것인지를 최종 결정하는 주체
- 여러개의 Voter들을 가질 수 있으며, Voter 들로부터 접근허용, 거부, 보류에 해당하는 각각의 값을 리턴받고 판단 및 결정
- 최종 접근 거부 시 예외 발생
- 접근 결정의 세가지 유형
    - AffirmativeBased
        - 여러개의 Voter 클래스 중 하나라도 접근 허가로 결론을 내면 접근 허가로 판단한다.
    - ConsensusBased
        - 다수표(승인 및 거부)에 의해 최종 결정을 판단한다
        - 동수일 경우, 기본은 접근 허가니나 allIfEqualGrantedDeninedDecisions을 false로 설정할 경우 접근 거부로 결정된다.
    - UnanimousBased
        - 모든 보터가 만장일치로 접근을 승인해야 하며 그렇지 않은 경우 접근을 거부한다.

# AccessDecisionVoter

- 판단을 심사하는 것(위원)
- Voter가 권한 부여 과정에서 판단하는 자료
    - Authentication - 인증 정보(user)
    - FilterInvocation - 요청 정보(antMatcher(”/user”))
    - ConfigAttributes - 권한 정보(hasRole(”USER”))
- 결정 방식
    - ACCESS_GRANTED: 접근 허용(1)
    - ACCESS_DENIED: 접근 거부(0)
    - ACCESS_ABSTAIN: 접근 보류(-1)
        - Voter 가 해당 타입의 요청에 대해 결정을 내릴 수 없는 경우

1. FilterSecurityInterceptor 인가처리
2. AccessDecisionManager가 3개의 파라미터(인증 정보, 요청 정보, 권한 정보) 를 근거로해서 인가처리여부 결정
3. AccessDecision의 return 값에 따라 권한판단을 심사
4. ACCESS_GRANTED or ACCESS_DENINED or ACCESS_ABSTAIN 리턴
5. ACCESS_DENINED
    1. AccessDeninedException 발생
