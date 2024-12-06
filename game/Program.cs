Random random = new Random();

while (true)
{
    switch(GetInput())
    {
        case "h":
        case "help": PrintHelp(); break;
        case "r":
        case "rules": PrintRules(); break;
        case "s":
        case "start": StartGame(); break;
        default:
            Console.WriteLine("Неверная команда");
            PrintHelp();
            break;
    }
}

string GetInput()
{
    Console.WriteLine("Введите команду");
    return Console.ReadLine();
}

void PrintHelp()
{
    Console.WriteLine("Посмотреть команды: \"help\" | \"h\"");
    Console.WriteLine("Начать игру: \"start\" | \"s\"");
    Console.WriteLine("Посмотреть правила \"rules\" | \"r\"");
}

void PrintRules()
{
    Console.WriteLine("Бросают три кости и у второго кубика считаем\n" +
        "значение невидимой грани (первый и третий\n" +
        "кубик видимая грань), выигрывает тот игрок, у\n" +
        "которого сумма выпавших очков равнялась одному\n" +
        "из двух чисел, названных им перед началом игры");
}

void StartGame()
{
    Console.WriteLine("Выберете один из следующих вариантов\n" +
        "1 -> Комьютер сам с собой\n" +
        "2 -> Человек против компьютера\n" +
        "3 -> Чекловек против человека\n" +
        "В ином случае Компьюетр сам поиграет");

    string vr = GetInput();
    switch(vr)
    {
        case "1": ComputerVSComputer(); break;
        case "2": PlayerVSComputer(); break;
        case "3": PlayerVSPlayer(); break;
        default: ComputerVSComputer(); break;
    }
}

void ComputerVSComputer()
{
    int[] firstPlayerChoice = {RandomChoice(), RandomChoice()};
    foreach (int choice in firstPlayerChoice) { Console.WriteLine("Компьютер выбрал" + choice); }

    int[] secondPlayerChoice = {RandomChoice(), RandomChoice()};
    foreach (int choice in secondPlayerChoice) { Console.WriteLine("Компьютер выбрал" + choice); }

    Play(firstPlayerChoice, secondPlayerChoice);
}

void PlayerVSComputer()
{
    int[] firstPlayerChoice = { GetPlayerPre(), GetPlayerPre() };
    int[] secondPlayerChoice = { RandomChoice(), RandomChoice() };
    foreach (int choice in secondPlayerChoice) { Console.WriteLine("Компьютер выбрал" + choice); }

    Play(firstPlayerChoice, secondPlayerChoice);
}

void PlayerVSPlayer()
{
    int[] firstPlayerChoice = { GetPlayerPre(), GetPlayerPre() };
    int[] secondPlayerChoice = { GetPlayerPre(), GetPlayerPre() };

    Play(firstPlayerChoice, secondPlayerChoice);
}

int GetPlayerPre()
{
    while(true) { 
        Console.WriteLine("Введите ваше предсказание(от 3 до 16 включительно)");
        int res = int.Parse(Console.ReadLine());
        if(res > 3 && res < 17) return res;
    }
}

void Play(int[] first, int[] second)
{
    int res = RandomSum();

    bool resultFirst = RoundPlayer(first, res);
    bool resultSecond = RoundPlayer(second, res);

    if (resultFirst != resultSecond)
    {
        if (resultFirst) Console.WriteLine("Победил первый игрок");
        else Console.WriteLine("Победил второй игрок");
    }
    else
    {
        Console.WriteLine("Ничья");
    }
}

bool RoundPlayer(int[] arr, int res)
{
    foreach (int i in arr) { if (res == i) { return true; } }
    return false;
}

int RandomSum()
{
    int sum = RandomDice();
    sum += RandomDice();
    sum += RandomDice();
    Console.WriteLine("Сумма кубиков: " + sum);
    return sum;
}

int RandomDice()
{
    return random.Next(6) + 1;
}

int RandomChoice()
{
    return random.Next(15) + 3;
}