# convenience-store-recommendation

주변 편의점 추천 서비스

이 프로젝트는 외부 API (카카오 주소 검색 API, 카카오 키워드 장소 검색 API, 카카오 지도 API) 를 활용하여 사용자의 주소를 중심으로 반경 10KM 이내의 최대 3개의 주변 편의점을 추천받는다.

사용자의 주소는 카카오 우편 번호 서비스를 사용하여 입력받는다.

길안내 URL 은 사용자에게 제공되기 때문에 가독성을 위해 base62 를 통해 인코딩된 shorten url 로 제공된다.

## 개발 환경

* Java 11
* Gradle 7.6
* Spring Boot 2.6.7

## 기술 세부 스택

Spring Boot

* Spring Web
* Lombok
* Spring Boot DevTools
* Spring Data JPA
* MariaDB
* Spring Retry
* Spock
* Testcontainers

그 외

* Handlebars
* Base62
* Docker
* AWS EC2
