- Создать два текстовых файла: "Pets"(Домашние животные) и "Pack animals"(вьючные животные), используя команду `cat` в терминале Linux. В первом файле перечислить собак, кошек и хомяков. Во втором — лошадей, верблюдов и ослов.
   - Объединить содержимое этих двух файлов в один и просмотреть его содержимое.
   - Переименовать получившийся файл в "Human Friends"(.
Пример конечного вывода после команды “ls” :
Desktop Documents Downloads  HumanFriends.txt  Music  PackAnimals.txt  Pets.txt  Pictures  Videos

Создание файлов "Pets.txt" и "PackAnimals.txt":

echo "Собаки, кошки, хомяки" > Pets.txt
echo "Лошади, верблюды, ослы" > PackAnimals.txt

Объединение содержимого файлов в один и просмотр его содержимого:

cat Pets.txt PackAnimals.txt > HumanFriends.txt
cat HumanFriends.txt

Переименование файла в "HumanFriends.txt":

mv HumanFriends.txt "Human Friends.txt"

Просмотр содержимого каталога:

ls

Вывод в консоль: