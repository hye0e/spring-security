### AuthenticationManager
- AuthenticationManager class가 인증처리를 하지 않음
- AuthenticationProvider 목록 중에서 인증 처리 요건에 맞는 AuthenticationProvider를 찾아 인증처리를 위임한다
- 부모 ProviderManager를 설정하여 AuthenticationProvider를 계속 탐색 할 수 있다.

### AuthenticationManager 역할
- ProviderManager를 생성
