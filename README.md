# Проект по автоматизации тестирования сайта сети аптек [<img width="20%" title="Rigla" src="media/icons/Rigla.svg"/>](https://rigla.ru/)
>«Ригла» - сеть аптек. Большинство лекарств поставляется в аптеки сети «Ригла» крупнейшим в России фармацевтическим дистрибьютором «Протек», который закупает их напрямую у официальных производителей. «Ригла» располагает широким ассортиментом лекарственных средств, также здесь же можно найти и редкие рецептурные препараты и многое другое.


## :receipt: Содержание
- [Используемый стек](#computer-используемый-стек)
- [Описание проекта](#bookmark_tabs-описание-проекта)
- [Реализованные проверки](#heavy_check_mark-реализованные-проверки)
  - [UI тесты](#pencil2-ui-тесты)
  - [API тесты](#pencil2-api-тесты)
  - [Ручные тесты](#pencil2-ручные-тесты)
- [Запуск тестов](#large_blue_circle-запуск-тестов)
  - [Локальный запуск](#desktop_computer-локальный-запуск)
  - [Удаленный запуск на Selenoid](#robot-удаленный-запуск-на-selenoid)
  - [Сборка в Jenkins](#-сборка-в-jenkins)
- [Allure-отчет](#-allure-отчет)
- [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)
  - [Результаты запуска из Jenkins](#результаты-запуска-из-jenkins)
  - [Запуск из TestOps](#запуск-из-testops)
- [Интеграция с JIRA](#-интеграция-с-jira)
- [Уведомления в Telegram](#-уведомления-в-telegram)
- [Видео примера запуска тестов в Selenoid](#-видео-примера-запуска-тестов-в-selenoid)

## :computer: Используемый стек
<p align="center">
<a href="https://www.jetbrains.com/idea/"><img width="6%" title="IntelliJ IDEA" src="media/icons/Intelij_IDEA.svg"/></a>
<a href="https://www.java.com/"><img width="6%" title="Java" src="media/icons/Java.svg"/></a>
<a href="https://selenide.org/"><img width="6%" title="Selenide" src="media/icons/Selenide.svg"/></a>
<a href="https://aerokube.com/selenoid/"><img width="6%" title="Selenoid" src="media/icons/Selenoid.svg"/></a>
<a href="https://github.com/allure-framework/allure2"><img width="6%" title="Allure Report" src="media/icons/Allure_Report.svg"/></a>
<a href="https://qameta.io/"><img width="6%" title="Allure TestOps" src="media/icons/Allure_TestOps.svg"/></a>
<a href="https://gradle.org/"><img width="6%" title="Gradle" src="media/icons/Gradle.svg"/></a>
<a href="https://junit.org/junit5/"><img width="6%" title="JUnit5" src="media/icons/JUnit5.svg"/></a>
<a href="https://github.com/"><img width="6%" title="GitHub" src="media/icons/Github.webp"/></a>
<a href="https://www.jenkins.io/"><img width="6%" title="Jenkins" src="media/icons/Jenkins.svg"/></a>
<a href="https://telegram.org/"><img width="6%" title="Telegram" src="media/icons/Telegram.svg"/></a>
<a href="https://www.atlassian.com/ru/software/jira"><img width="6%" title="Jira" src="media/icons/Jira.webp"/></a>
</p>


## :bookmark_tabs: Описание проекта

- Проект состоит из UI, API и ручных тестов
- Ручные тесты добавлены в ```Allure TestOps```
- Автоматические тесты реализованы на языке ```Java```
- В качестве сборщика используется ```Gradle```
- Используются фреймворки ```JUnit 5``` и ```Selenide```
- Используется шаблон проектирования ```PageObject```
- Используется технология ```Owner``` для конфигурации тестов
- Используется библиотека ```Lombok``` для моделей в API тестах
- При запуске тестов браузер может запускаться локально или в ```Selenoid```
- Реализована возможность запуска тестов в ```Jenkins```
- Реализована возможность запуска тестов из ```Allure TestOps```
- Реализована интеграция с ```Jira```
- Настроена отправка уведомлений о результатах прохождения в чат-бот ```Telegram```
- По завершении прохождения автотестов генерируется ```Allure Report```

---

## :heavy_check_mark: Реализованные проверки

### :pencil2: UI тесты
:green_circle: Авторизация
- Проверка успешного логина
- Проверка неуспешного логина из-за капчи
- Проверка неуспешного логина без почты
- Проверка неуспешного логина без пароля
- Проверка неуспешного логина с неверными кредами

:green_circle: Избранные товары
- Проверка добавления товара в избранное
- Проверка удаления продукта из избранного

:green_circle: Корзина
- Проверка добавления товара в корзину
- Проверка добавления двух одинаковых товаров в корзину через +
- Проверка увеличения количества товара в корзине
- Проверка уменьшения количества товара в корзине
- Проверка удаления товара из корзины
- Проверка очистки корзины

### :pencil2: API тесты
:green_circle: Избранные товары
- Проверка добавления товара в избранное
- Проверка добавления несуществующего товара в избранное
- Проверка добавления товара в избранное дважды
- Проверка добавления товара в избранное без авторизации
- Проверка удаления товара из избранного
- Проверка удаления товара с несуществующим id из избранного
- Проверка удаления товара из избранного без авторизации
- Проверка получения списка товаров из избранного
- Проверка получения пустого списка товаров из избранного
- Проверка получения списка товаров из избранного без авторизации

### :pencil2: Ручные тесты
- Проверка успешного логина с почтой и паролем
- Проверка получения списка избранных товаров с несколькими товарами

---

## :large_blue_circle: Запуск тестов

## :desktop_computer: Локальный запуск
Параметры для локального запуска в терминале IDE:
- тег/задача
  - ```test``` - все тесты
  - ```web``` - UI тесты
  - ```api``` - API тесты
- где запускать браузер
  -  ```-DisRemote=false``` - или не указывать этот параметр совсем, т.к. значение по умолчанию - false (== запускаем браузер локально)
- логин и пароль
  - ```-Demail``` и ```-Dpassword``` - только для UI тестов. Но т.к. на сайте невозможно обмануть капчу и несколько тестов помечены как Disabled, то можно указать абсолютно любые логин и пароль
- настройки браузера
  - ```-Dbrowser```, ```-DbrowserVersion```, ```-DbrowseSize``` - если не указывать, то используются значения по умолчанию (заданные в local.properties)


```java
gradle clean test -Demail="test_email@gmail.com" -Dpassword="123456"

gradle clean web
-Demail="test_email@gmail.com"
-Dpassword="123456"
-DisRemote=false
-Dbrowser=chrome
-DbrowserVersion=127
-DbrowseSize=1600x1200

gradle clean api
```

## :robot: Удаленный запуск на Selenoid
Параметры для удаленного запуска в терминале IDE:
- тег/задача
  - ```test``` - все тесты
  - ```web``` - UI тесты
  - ```api``` - API тесты
- где запускать браузер
  -  ```-DisRemote=true```
  -  ```-DwdHost=selenoid.autotests.cloud```
  -  ```-DselenoidLogin=...```
  -  ```-DselenoidPassword=...```
- логин и пароль
  - ```-Demail``` и ```-Dpassword``` - только для UI тестов. Но т.к. на сайте невозможно обмануть капчу и несколько тестов помечены как Disabled, то можно указать абсолютно любые логин и пароль
- настройки браузера
  - ```-Dbrowser```, ```-DbrowserVersion```, ```-DbrowseSize``` - если не указывать, то используются значения по умолчанию (заданные в remote.properties). В Selenoid ферме представлены не все браузеры и не все версии, поэтому указывать можно только те, которые там имеются:
    - chrome: 125, 126


```java
gradle clean test
-DisRemote=true
-Demail="test_email@gmail.com"
-Dpassword="123456"
-DwdHost=selenoid.autotests.cloud
-DselenoidLogin=...
-DselenoidPassword=...

gradle clean web
-DisRemote=true
-Demail="test_email@gmail.com"
-Dpassword="123456"
-DwdHost=selenoid.autotests.cloud
-DselenoidLogin=...
-DselenoidPassword=...
-Dbrowser=chrome
-DbrowserVersion=125
-DbrowseSize=1920x1200

gradle clean api
-DisRemote=true
-DwdHost=selenoid.autotests.cloud
-DselenoidLogin=...
-DselenoidPassword=...
```

## <img width="3%" title="Jenkins" src="media/icons/Jenkins.svg"/> [Сборка в Jenkins](https://jenkins.autotests.cloud/job/C27-petrova_di-rigla/)
<img width="50%" title="Jenkins" src="media/img/Jenkins_job.png"/>

### :writing_hand: Параметры сборки в Jenkins, задаваемые пользователем
<img width="50%" title="Jenkins" src="media/img/Jenkins_job_params.png"/>

```TASK``` - название задачи. Значение по умолчанию - ```test```  
```WDHOST``` - адрес удаленного браузера (selenoid). Значение - ```selenoid.autotests.cloud```  
```BROWSER``` - браузер для запуска тестов. Значение по умолчанию - ```chrome```  
```BROWSER_VERSION``` - версия браузера. Значение по умолчанию - ```126```  
```BROWSE_SIZE``` - размер окна браузера. Значение по умолчанию - ```1920x1080```  

### :arrow_right: Параметры сборки в Jenkins, передаваемые из установленных значений:

```USER_EMAIL``` - логин пользователя на сайте rigla.ru  
```USER_PASSWORD``` - пароль пользователя на сайте rigla.ru  
```SELENOID_LOGIN``` - логин пользователя для Selenoid фермы  
```SELENOID_PASSWORD``` - пароль пользователя для Selenoid фермы  

```java
clean
${TASK}
-DwdHost=${WDHOST}
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowseSize=${BROWSE_SIZE}
-Demail=${USER_EMAIL}
-Dpassword=${USER_PASSWORD}
-DselenoidLogin=${SELENOID_LOGIN}
-DselenoidPassword=${SELENOID_PASSWORD}
-DisRemote="true"
```

:gear: Запуск в Jenkins:
1. Открыть [сборку](https://jenkins.autotests.cloud/job/C27-petrova_di-rigla/)
2. Нажать ```Собрать с параметрами```/```Build with parameters```
3. Выбрать значения или оставить установленные по умолчанию
4. Нажать ```Собрать```/```Build```
5. Результат - в логах самой сборки, в Allure-отчете или в Allure TestOps

---

## <img width="3%" title="Allure Report" src="media/icons/Allure_Report.svg"/> [Allure-отчет](https://jenkins.autotests.cloud/job/C27-petrova_di-rigla/allure/)

После выполнения сборки в Jenkins формируется отчет в Allure.  
В блоке ```История сборок/Build History``` напротив конкретной сборки отображается значок [<img width="2%" title="Allure Report" src="media/icons/Allure_Report.svg"/>](https://jenkins.autotests.cloud/job/C27-petrova_di-rigla/20/allure/), при нажатии на который открывается страница со сформированным html-отчетом и тестовой документацией.

На основной странице представлена информация о пройденных тестах, тестовые наборы, статистика проходов, распределение по функционалу.  
<img width="50%" title="Allure Overview" src="media/img/Allure_overview.png"/>

Переходя на страницу конкретного тестового набора, можно увидеть список всех пройденных и непройденных тестов, а также детали каждого теста.  
<img width="50%" title="Allure Test" src="media/img/Allure_test.png"/>

Помимо обычного логирования каждого шага теста, в конце каждого UI теста фиксируется следующая информация:  
<img width="50%" title="Allure Detailed Finish" src="media/img/Allure_detailedFinish.png"/>
- последний скриншот
- исходных код страницы
- логи в консоли браузера
- видео прохождения теста

А для каждого API теста запрос и ответ логируются в удобочитаемом виде:  
<img width="50%" title="Allure Request" src="media/img/Allure_request.png"/>  
<img width="50%" title="Allure Response" src="media/img/Allure_response.png"/>  

---

## <img width="3%" title="Allure TestOps" src="media/icons/Allure_TestOps.svg"/> [Интеграция с Allure TestOps](https://allure.autotests.cloud/project/4372/dashboards)
### Результаты запуска из Jenkins
Во время выполнения сборки в Jenkins данные о запуске, тестах и результатах появляются в Allure TestOps.  
В блоке ```История сборок/Build History``` напротив конкретной сборки отображается значок <img width="2%" title="Allure TestOps" src="media/icons/Allure_TestOps.svg"/>, при нажатии на который открывается страница связанного [запуска](https://allure.autotests.cloud/launch/41256) (вкладка "Обзор").  
<img width="50%" title="Allure TestOps Launch Overview" src="media/img/Allure_TestOps_launch_overview.png"/>  

На вкладке "Результаты тестов" можно просмотреть [результат](https://allure.autotests.cloud/launch/41256/tree?treeId=8630) выполнения каждого теста:  
<img width="50%" title="Allure TestOps Launch Test Results" src="media/img/Allure_TestOps_launch_testResult.png"/>

А также добавить в запуск другие тесты, например, ручные, и пройти их:  
<img width="50%" title="Allure TestOps Launch Manual Tests" src="media/img/Allure_TestOps_launch_manual.png"/>

### Запуск из TestOps
Запустить тесты можно и из Allure TestOps:   
<img width="50%" title="Allure TestOps Job Params" src="media/img/Allure_TestOps_job_params.png"/>  
1. Перейти в [джобы проекта](https://allure.autotests.cloud/project/4372/jobs)
2. Нажать кнопку "Запустить джобу"
3. Задать название
4. В разделе "Окружение" задать значения, если нужно задать отличные от значений по умолчанию
5. Выбрать тесты для запуска
6. Нажать "Отправить"

---

## <img width="3%" title="Jira" src="media/icons/Jira.webp"/> [Интеграция с JIRA](https://jira.autotests.cloud/secure/Dashboard.jspa)
В проекте также реализована интеграция между Allure TestOps и Jira.  
В ходе тестирования был заведен дефект на результат одного из API тестов: [HOMEWORK-1322](https://jira.autotests.cloud/browse/HOMEWORK-1322).  
Этот дефект привязан к соответствующему тест-кейсу в TestOps:  
<img width="50%" title="Jira ticket in case" src="media/img/Allure_TestOps_case_jira.png"/>

Так же эту связь можно увидеть и в самом тикете в Jira:  
<img width="50%" title="Cases in Jira ticket" src="media/img/Allure_TestOps_jira_case.png"/>


## <img width="3%" title="Telegram" src="media/icons/Telegram.svg"/> Уведомления в Telegram
После завершения сборки специальный бот, созданный в ```Telegram```, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне тестов.  
<img width="30%" title="TG Notification" src="media/img/TG.png"/>

## <img width="3%" title="Selenoid" src="media/icons/Selenoid.svg"/> Видео примера запуска тестов в Selenoid
Как упоминалось ранее, для каждого UI теста записывается видео его выполнения.  
<img width="50%" title="Test Video" src="media/img/TestVideo.gif"/>
