# Проект по автоматизации тестирования сайта сети аптек [<img width="20%" title="Rigla" src="media/icons/Rigla.svg"/>](https://rigla.ru/)
>Наши аптеки – это многопрофильные центры здоровья и красоты европейского уровня. Мы уверены, что успеха и благополучия гораздо легче добиться здоровым людям. Жить полной жизнью, использовать каждый шанс, который дает фортуна, – такова философия современного человека. Не тратьте времени на болезни, улыбайтесь людям, занимайтесь спортом, гордитесь собой, путешествуйте и уверенно идите к своей цели!

## :receipt: Содержание
- [Используемый стек](#computer-используемый-стек)
- [Описание проекта](#bookmark_tabs-описание-проекта)
- [Реализованные проверки](#heavy_check_mark-реализованные-проверки)
  - [UI тесты](#pencil2-ui-тесты)
  - [API тесты](#pencil2-api-тесты)
  - [Ручные тесты](#pencil2-ручные-тесты)
- [Запуск тестов](#large_blue_circle-запуск-тестов)
  - [Локальный запуск с запуском браузера локально](#локальный-запуск-с-запуском-браузера-локально)
  - [Локальный запуск с запуском браузера в Selenoid](#локальный-запуск-с-запуском-браузера-в-selenoid)
  - [Удаленный запуск на Selenoid](#удаленный-запуск)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Allure-отчет](#-allure-отчет)
- [Allure-TestOps](#-allure-testops)
- [Задача в JIRA](#-задача-в-jira)
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
- Используется технология ```Owner``` для конфигурации тестов
- Используется ```Lombok``` для моделей в API тестах
- При запуске тестов браузер может запускаться локально или в ```Selenoid```
- Реализована возможность запуска тестов в ```Jenkins```
- Реализована возможность запуска тестов из ```Allure TestOps```
- Реализована интеграция с ```Jira```
- Настроена отправка уведомлений о результатах прохождения в чат-бот ```Telegram```
- По завершении прохождения автотестов генерируется ```Allure Report```

## :heavy_check_mark: Реализованные проверки

### :pencil2: UI тесты
- :white_check_mark: Авторизация
  - Успешный логин
  - Неуспешный логин из-за капчи
  - Неуспешный логин без почты
  - Неуспешный логин без пароля
  - Неуспешный логин с неверными кредами
- :white_check_mark: Избранные товары
  - Добавление продукта в Избранное
  - Удаление продукта из Избранного
- :white_check_mark: Корзина
  - Добавление товара в корзину
  - Добавление двух одинаковых товаров в корзину через +
  - Увеличение количества продукта в корзине
  - Уменьшение количества продукта в корзине
  - Удаление продукта из корзины
  - Очистка корзины

### :pencil2: API тесты
- :white_check_mark: Избранные товары
  - Добавление товара в избранное
  - Добавление несуществующего товара в избранное
  - Добавление товара в избранное дважды
  - Добавление товара в избранное без авторизации
  - Удаление товара из избранного
  - Удаление товара с несуществующим id из избранного
  - Удаление товара из избранного без авторизации
  - Получение списка товаров из избранного
  - Получение пустого списка товаров из избранного
  - Получение списка товаров из избранного без авторизации

### :pencil2: Ручные тесты
- Успешный логин с почтой и паролем
- Получение списка избранных товаров с несколькими товарами

---

## :large_blue_circle: Запуск тестов

### Локальный запуск с запуском браузера локально
```bash
gradle clean test
gradle clean web -Demail="petrova.tpu@gmail.com" -Dpassword="123456" -DisRemote="false"
```

### Локальный запуск с запуском браузера в Selenoid
```bash
gradle clean test
-DwdHost="selenoid.autotest.cloud"
-DselenoidLogin="user1"
-DselenoidPassword="1234"
-DisRemote="true"
```

```bash
gradle clean login
-Demail="petrova.tpu@gmail.com"
-Dpassword="123456"
-DwdHost="selenoid.autotest.cloud"
-DselenoidLogin="user1"
-DselenoidPassword="1234"
-DisRemote="true"
```


### Теги (задачи)
