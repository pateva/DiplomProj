Софтуерна система за управление на
процес на дипломиране

Задание

Да се реализира уеб приложение „Дипломиране“, което да служи за управление на етапите,
през които трябва да се премине в процес на дипломиране на студент. Процесът на
дипломиране протича по следния начин: в системата се подава заявление за дипломна работа,
в което се включва информация за: 1. темата на дипломната работа, 2. целта ѝ, 3. задачите,
които ще се решат, за да се постигне целта, 4. използваните технологии за бъдещото решение,
5. студентът, който ще изготви дипломната работа и 6. преподавателят, който ще му бъде
дипломен ръководител. Заявлението за дипломна работа се подава от преподавател,
разглежда се и се одобрява или не се одобрява от департамента, в който се осигурява
обучението. Ако заявлението е одобрено, студентът преминава към качване на готовата
дипломна теза в системата. Дипломната теза има заглавие, текст и се пази датата на нейното
качване в системата. Качените дипломни работи се рецензират от регистриран в системата
преподавател. Рецензията има дата на качване, текст и заключение, дали е положителна или
отрицателна. След качване на рецензия, студентът преминава към етап на защита на
дипломната работа. В една процедура по защита на дипломна работа участват група
преподаватели и защитават група студенти. В системата се регистрира оценката, която
дипломантът получава при защитата на дипломната работа. В системата трябва да се
реализират две роли: студент и преподавател.
Функционални изисквания на системата
Уеб приложението „Дипломиране“ трябва да включва минимум следните функционалности:
1. Въвеждане, показване, редактиране и изтриване на данни за:
a. Задание за дипломна работа, с описаните по-горе характеристики;
b. Студент, с име и факултетен номер;
c. Преподавател, с име длъжност. Длъжностите са: асистент, главен асистент,
доцент и професор;
d. Дипломна защита, с дата на провеждане.
2. Преподавателите в системата могат да обработват данните в нея. Студентите могат
само да проследяват информация, свързана с тяхната процедура по подаване на
задание и дипломиране и да виждат всички одобрени теми за дипломни работи.
Студентите качват единствено дипломната работа.
3. Справки за:
a. Всички одобрени задания за дипломна работа.
b. Всички теми на дипломни работи, които в заглавието си съдържат определен
символен низ.
c. Всички одобрени задания за дипломна работа, на които е ръководител
определен преподавател.
d. Всички студенти, които са се дипломирали в даден период от време.
e. Всички дипломни работи, които са защитени с оценка в определен диапазон.
f. Броят на студените, които са получили отрицателна рецензия на дипломна
работа.
g. Средният брой на явилите се на дипломна защита студенти в рамките на
опреден период.
h. Броят на защитилите успешно дипломна работа на определен преподавател.
Технологични изисквания
Да се разработи уеб приложение със Spring Boot, базирано на RESTful уеб услуги с база данни и
потребителски интерфейс. Приложението трябва да осигури възможност за въвеждане,
редактиране, изтриване и показване на данните,описани в изискванията. Необходимо е да се
реализира валидиране на данните, с които се работи, механизъм за управление на
изключенията и тестове.
