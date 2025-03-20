# Refactoring 2nd Edition

> 리팩터링 2판을 읽고 실습한 내용을 정리하는 레포지토리

<br/>

## 목차

| Chapter | 관련 브랜치                                                                                                                                              | 내용 요약                                                                                                                                | 학습 기록 및 느낌                                                                   |
|---------|-----------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| 1       | [리팩터링: 첫번째 예시](https://github.com/junpakPark/refactoring-2nd-edition/tree/chapter01/src/main/java/me/junpak/refactoring/chapter1/after)             | [Ch01.md](https://github.com/junpakPark/refactoring-2nd-edition/blob/chapter01/src/main/java/me/junpak/refactoring/chapter1/Ch01.md) | [Pull Request](https://github.com/junpakPark/refactoring-2nd-edition/pull/1) |
| 1 - 1   | [첫번째 예시 내 맘대로 리팩터링 하기](https://github.com/junpakPark/refactoring-2nd-edition/tree/chapter01-1/src/main/java/me/junpak/refactoring/chapter1/current) | _                                                                                                                                    | [Pull Request](https://github.com/junpakPark/refactoring-2nd-edition/pull/2) |
| 2       | [리팩터링 원칙](https://github.com/junpakPark/refactoring-2nd-edition/tree/chapter02)                                                                     | [Ch02.md](https://github.com/junpakPark/refactoring-2nd-edition/blob/chapter02/src/main/java/me/junpak/refactoring/chapter2/Ch02.md) | [Pull Request](https://github.com/junpakPark/refactoring-2nd-edition/pull/3) |
| 3       | [코드에서 나는 악취](https://github.com/junpakPark/refactoring-2nd-edition/tree/chapter03)                                                                  | [Ch03.md](https://github.com/junpakPark/refactoring-2nd-edition/blob/chapter03/src/main/java/me/junpak/refactoring/chapter3/Ch03.md) | [Pull Request](https://github.com/junpakPark/refactoring-2nd-edition/pull/4) |
| 4       | [테스트 구축하기](https://github.com/junpakPark/refactoring-2nd-edition/tree/chapter04)                                                                    | _                                                                                                                                    | [Pull Request](https://github.com/junpakPark/refactoring-2nd-edition/pull/5) |
| 5       | [리팩터링 카탈로그 보는 법](https://github.com/junpakPark/refactoring-2nd-edition/tree/chapter05)                                                              | [Ch05.md](https://github.com/junpakPark/refactoring-2nd-edition/blob/chapter05/src/main/java/me/junpak/refactoring/chapter5/Ch05.md) | [Pull Request](https://github.com/junpakPark/refactoring-2nd-edition/pull/6) |
| 6       | [기본적인 리팩터링](https://github.com/junpakPark/refactoring-2nd-edition/tree/chapter06)                                                                   | [Ch06.md](https://github.com/junpakPark/refactoring-2nd-edition/blob/chapter06/src/main/java/me/junpak/refactoring/chapter6/Ch06.md) | [Pull Request](https://github.com/junpakPark/refactoring-2nd-edition/pull/7) |
| 7       | 캡슐화                                                                                                                                                 | Ch07.md                                                                                                                              | Pull Request                                                                 |
| 8       | [기능 이동](https://github.com/junpakPark/refactoring-2nd-edition/tree/chapter08)                                                                       | [Ch08.md](https://github.com/junpakPark/refactoring-2nd-edition/blob/chapter08/src/main/java/me/junpak/refactoring/chapter8/Ch08.md) | [Pull Request](https://github.com/junpakPark/refactoring-2nd-edition/pull/8) |
| 9       | [데이터 조직화](https://github.com/junpakPark/refactoring-2nd-edition/tree/chapter09)                                                                     | [Ch09.md](https://github.com/junpakPark/refactoring-2nd-edition/blob/chapter09/src/main/java/me/junpak/refactoring/chapter9/Ch09.md) | [Pull Request](https://github.com/junpakPark/refactoring-2nd-edition/pull/9) |
| 10      | 조건부 로직 간소화                                                                                                                                          | Ch10.md                                                                                                                              | Pull Request                                                                 |
| 11      | API 리팩터링                                                                                                                                            | Ch11.md                                                                                                                              | Pull Request                                                                 |
| 12      | 상속 다루기                                                                                                                                              | Ch12.md                                                                                                                              | Pull Request                                                                 |

<br/>

### 작성 방법

- 리팩터링 2판의 예제를 `Java`로 작성한다.
- 각 챕터별로 `ChapterXX` 형식의 브랜치를 만들고, 해당 챕터의 예제를 작은 스텝으로 나누어 리팩터링한다.
    - 리팩터링 전 코드: `before` 폴더
    - 리팩터링 후 코드: `after` 폴더
    - 내용 요약: `ChXX.md`
- 각 단계를 진행할 때마다 테스트 코드를 통해 동작을 보장하고, 적절한 커밋 메시지를 남긴다.
- 학습 기록 및 느낌은 `Pull Request`를 통해 기록한다.

<br/>

### 개발 환경 및 도구

- **JDK**: 17 이상 (예: OpenJDK 17)
- **빌드 툴**: Gradle 또는 Maven (원하는 빌드 툴 사용 가능)
- **테스트 프레임워크**: JUnit (버전 5 권장)

위와 같은 환경을 사용하여 코드를 작성하고 테스트합니다.  
개발 환경이 다르더라도 동일한 언어나 테스트 프레임워크를 사용하면 대부분 문제없이 실행될 것입니다.

<br/>

### 참고 자료

- [Martin Fowler Official Site](https://martinfowler.com/)
- [『리팩터링 2판』(한빛미디어, 2020) 지원 사이트](https://github.com/WegraLee/Refactoring)
