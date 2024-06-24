ㅡㅡㅡㅡㅡ  EC2  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

껏다키면 xshell, 인텔리제이 설정 변경, 퍼블릭 주소 변경

키페어이름 : jang

퍼블릭 IPv4 주소 : 43.201.101.136  -- > ssh 접속 ( ec2 껏다 킬시 매번 바뀜 )

id:  ubuntu

xshell 연결 -> 호스트에 퍼블릭 ipv4
사용자 인증 -> 이름 : ubuntu , public key

인텔리제이 ec2 연결
배포경로:home/ubuntu/crawl_project
배포 -> 호스트 퍼블릭ip 사용자이름 ubuntu 인증 키쌍

ㅡㅡㅡㅡ  RDS   ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

db 테이블 이름 : crawl_data

[DB id 초기화문]
use crawl_data;
ALTER TABLE price AUTO_INCREMENT = 1;
ALTER TABLE product AUTO_INCREMENT = 1;
ALTER TABLE brand AUTO_INCREMENT = 1;

ㅡㅡㅡㅡㅡ 로컬파일위치 & 실행법ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

크롤링 파일 위치 (로컬) 'vscode'
C - startcoding - coupang - crawler.py

스프링 파일 위치 (로컬) '인텔리제이'
C - proj - project-master

로컬 리액트프로젝트 위치 
C:\react\Market_Web_main_marketweb 을 vscode 에서 폴더 열기 후 npm start

★ 리액트 프로젝트 실행법 :

리액트 프로젝트 폴더에서 
npm init
후
npm install --legacy-peer-deps
후
npm install recharts --legacy-peer-deps
후
npm install @fortawesome/fontawesome-svg-core @fortawesome/free-solid-svg-icons @fortawesome/react-fontawesome --legacy-peer-deps
후
npm install styled-components --legacy-peer-deps
후
npm start

ec2 프로젝트 제거 : rm -rf 폴더명/

cors 열어주기 localhost:3000, 210.110.32.134:3000 <- 
스프링에서 local과, 210 서버에서 가져갈수잇도록 열어줌(내 ip에서만 접속가능)
외부 열어주려면 nginx 설치 후 사용

★ 스프링 프로젝트 실행법 ( ec2 ) :
cd crawl_project -> ./gradlew clean build -x test -> cd build/libs ->
nohup java -jar demo-0.0.1-SNAPSHOT.jar &  (백그라운드 실행, EC2 콘솔 접속 끊더라도 실행파일을 계속 실행)

 (로그 확인 명령어)
cat nohup.out

★ 스프링 프로젝트 종료법 ( ec2 ) :
jobs -> fg %(인덱스)   ex) fg %1 -> 컨트롤c
or
ps aux | grep java -> kill 숫자

ㅡㅡㅡㅡ서버 설치 연동ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

ec2서버에 node , npm , nvm 설치 완료

axios 설치 서버, 로컬 둘다 완료

ㅡㅡ페이지 구성ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

메인 홈화면 (제품추천순 제품전체) : /api/products/    ex) http://localhost:8080/api/products/
필요 데이터 :
상품id(id), 상품사진(img_url), 브랜드이름(brand_name), 제품명(title), 낮은가격(coupon_price), 할인률(discount_rate)

카테고리별 페이지 : /api/products/category/{카테고리id}   ex) 노트북제품전체, 태블릿제품전체
필요 데이터 :
상품사진(img_url), 브랜드이름(brand_name), 제품명(title), 낮은가격(coupon_price), 할인률(discount_rate)

브랜드별 페이지 : /api/products/brand/{브랜드id}        ex)브랜드가 apple인 제품 전체, 삼성인 제품 전체
필요 데이터 :
상품사진(img_url), 브랜드이름(brand_name), 제품명(title), 낮은가격(coupon_price), 할인률(discount_rate)

개별상품 상세페이지 :  /api/products/{제품id}                ex) 제품 id가 x인 제품 상세페이지
필요 데이터 : 제품id(id), 카테고리id(category_id), 브랜드id(brand_id), 브랜드이름(brand_name), 제품명(title), 사진url(img_url), 상품링크(url), 상품옵션(prod_option), 설명(description), 평점(rating), 배송(delivery), 가격 3개(origin_price, sale_price, coupon_price), 할인률(discount_rate)

ㅡㅡ☆할 일 ★완료 목록ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

★ 별점(rating) -> product 테이블 
★ 배송(delivery) -> price 테이블 

★ 각 페이지별 필요한 데이터 json형식으로 보내도록 구성 

★ cors 설정 

★ product_id에 price값이 여러개라 오류가 남 -> 제일 최신 가격으로 설정 

★ ec2에서 스프링 배포 후 외부에서 접속해보기 
   스프링 ec2에서 실행 후 http://3.34.188.16:8080/api/products/ <- 이렇게 axios get 주소에 넣으면 동작 o
	cors 열어주기

★ 가격 그래프 하려면 가격 내역 있어야함 하루 2번 크롤링 -> 아침 9시[ o ] , 오후 9시 크롤링 [   ] 

★ 크롤링 db 저장 확실한 제품만 하기, 일시품절 제외 ( 필름, 케이스 제외 )
    ( how : 카테고리 페이지 말고 검색 후 크롤링? or 최저가격 2만 설정 후 그 위로만 크롤링 )

★ 스프링 리액트 연동 (디테일, 필터, 카테고리랑 브랜드 , 개인화면 연동)

★ 데이터를 다 보내고 리액트 상 뜨는거만 60개 뜨게 ( 노트북같은거 정렬하면 없어짐 )

★ 메인페이지 정렬 로직 구현 어케할지( ex) 할인률순, 변동폭순, 가격변동률순으로 정렬 ) 
    '가격변동순' 구하는법 ( how : 가장 최근 가격에서 그 직전 가격 빼서 할인률 구해서 )

★ 가격을 안끌어왔을때 전에 남은 가격변동률이 그대로 남는다 해결하기
(크롤링이 안되면 전의 데이터로 정렬이 된다. 가장 최근 시간의 날짜를 찾거나 시간으로 크롤링된 자료만 정렬)

★ 차트 가격 데이터 넘기기 (PriceController...) 선  차트
axios.get(`http://3.34.188.16:8080/api/prices/product/${productId}`) 
<- 제품id의 가격 전체 정보를 오름차순(과거부터최신)으로 넘긴다
제품의 id 같이 넘기기 product테이블의 id 넘겨서 매칭할수 있도록 , 날짜 넘기기 하루 두개

★ 엔진엑스 외부접속 설정 : 엔진엑스 cors 오류 잘 안됨
git clone https://github.com/REVE97/Market_Web.git
 후 Market_Web -> market_web 에서 npm init 등등 하고 npm run build 한후 build 파일을 
mkdir -p src/main/resources/static 만들고 build 를 static에 넣기
cp -r /home/ubuntu/Market_Web/market-web/build/* /home/ubuntu/crawl_project/src/main/resources/static/
백그라운드 끄고 ./gradlew clean build -x test 후 재실행
실행 http://3.34.188.16:8080

★ date 하나에 대해서 최저가, 최고가 보내기 일봉 차트
ex) product_id : 1 / date : 2024-06-9 / high_price : 200000 / low_price : 180000
product_id : 1 / date : 2024-06-10 / high_price : 210000 / low_price : 190000
하루에 데이터가 두개있을때는 서로 비교 후 보내고, 하나있을때는 그 하나를 최고가 최저가 똑같이 설정해서 보내도록.
axios.get(`http://3.34.188.16:8080/api/prices/summary/${product_id}`) 

★ 판매량순 만들기
review_count 크롤링 해서 price 테이블에 저장 
-> price테이블 review_count 열 추가, 크롤링코드에서 추가
mainpage 보낼때 review_count도 같이 보내기 
-> mainpage dto 리뷰카운트 추가 , productmapper 에서 mainpage부분 리뷰카운트 보내기

★크롤링 자동화

★ 검색어 테이블 만들어서 db에 저장 후 뿌리기 (SearchKeywordController...)
검색어 저장 후 카운트 늘려서 카운트 높은 순으로 뿌리기
axios.post('http://3.34.188.16:8080/api/keywords') <-   검색어를 서버로 보내기
axios.get('http://3.34.188.16:8080:8080/api/keywords') <- 인기 검색어를 서버로부터 전달 요청
스케쥴러 이용해서 날짜 변경되면 카운트 초기화 하도록.

☆id, date 값 null 찍힘 , count keyword는 정상
1	카운트삼	3	2024-06-10
2	카운트일	1	2024-06-10
3	카운트육	6	2024-06-10

★ 회원가입 로그인 테이블 만들기ㅇ
user테이블, user엔티티,매퍼,서비스,컨트롤러, 시큐리티설정WebSecurityConfig
테이블 만들고 백엔드 세팅
http://localhost:8080/api/users/signup <- 회원정보 서버로 보내기
http://localhost:8080/api/users/login <- id,pw 서버로 보내기
페이지는 3.34.188.16, 회원가입,로그인은 210.110.32.134
★ 역대 최저가면 true, 아니면 false


재고를 직접 관리하고 가격책정까지 하는 국내 쇼핑몰은 쿠팡이 유일합니다.
이 때문에 타 쇼핑몰과 비교했을 때 공격적인 가격 책정을 하고 있고, 시시각각 변하는 다이나믹 프라이싱이 가능하기 때문에 소위 "특가"라 부를 만한 가격에 소비자들이 구매를 할 수 있습니다.
