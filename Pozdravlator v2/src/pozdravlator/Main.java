package pozdravlator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		ArrayListCollection arrayBirthday = new ArrayListCollection();
		Scanner scanner = new Scanner(System.in);
		H2DataBase db = new H2DataBase();
		db.createTableDB();
		
		arrayBirthday.getData();
		
		System.out.println(" ");

		String menuElem[] = {
				
				"1. Добавить новую запись.",
				"2. Удалить запись.",
				"3. Редактировать запись.",
				"4. Вывести список всех дней рождений.",
				"5. Выход."
				
		};
			
		while(true) {
			
			arrayBirthday.upcomingBirthday();
			
			for (int i = 0; i < menuElem.length; i++) {
						
				System.out.println(menuElem[i]);
						
			}
			
			System.out.print("Введите номер пункта меню: ");
			int activeElemMenu = scanner.nextInt();
			scanner.nextLine();
			
			switch(activeElemMenu) {
			
				case 1: arrayBirthday.setData(scanner);
				break;
				case 2: arrayBirthday.deleteElem(scanner);
				break;
				case 3: arrayBirthday.redactElem(scanner);
				break;
				case 4: arrayBirthday.printArrayBirthday();
				break;
				case 5: System.exit(0);
				break;
				default: System.err.println("Некорректный пункт меню!");
			
			}
			
			System.out.println(" ");
			
		}
		
	}

}
