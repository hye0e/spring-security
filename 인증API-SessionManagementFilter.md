### SessionManagementFilter
1. 세션 관리
2. 동시적 세션 제어
3. 세션 고정보호
4. 세션 생성 정책

### ConcurrentSessionFilter
- 매 요청마다 현재 사용자의 세션 만료 여부 체크
- 세션이 만료되었을 경우 즉시 만료처리

### 권한 설정
- 선언적 방식
  - URL 방식 
  - Method

### Method 방식
| 메소드                     | 동작                                 |
|-------------------------|------------------------------------|
| authenticated()         | 인증된 사용자의 접근을 허용                    |
| fullyAuthenticated()    | 인증된 사용자의 접근을 허용, rememberMe 인증제외   |
| permitAll()             | 무조건 접근을 허용                         |
| denyAll()               | 무조건 접근을 허용하지 않음                    |
| anonymous()             | 익명사용자의 접근을 허용 (익명사용자용만 가능)         |
| rememberMe()            | 기억하기를 통해 인증된 사용자의 접근을 허용           |
| access(String)          | 주어진 SpEL 표현식의 평가 결과가 true이면 접근을 허용 |
| hasRole(String)         | 사용자가 주어진 역할이 있다면 접근을 허용            |
| hasAuthority(String)    | 사용자가 주어진 권한이 있다면 접근을 허용            |
| hasAnyRole(String)      | 사용자가 주어진 권한이 있다면 접근을 허용            |
| hasAnyAuthority(String) | 사용자가 주어진 권한 중 어떤 것이라도 있다면 접근을 허용   |
| hasIpAddress(String)    | 주어진 IP로부터 요청이 왔다면 접근을 허용           |

