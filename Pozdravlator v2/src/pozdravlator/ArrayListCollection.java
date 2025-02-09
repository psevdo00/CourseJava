package pozdravlator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ArrayListCollection {

	private ArrayList<Birthday> birthdays;
	
	public ArrayListCollection() {
		
		birthdays = new ArrayList<Birthday>();
		
	}
	
	public void addElem(Birthday birthday) {

		birthdays.add(birthday);
		
	}
	
	public Birthday birthdayInfo(Scanner scanner) {
		
		System.out.print("\nВведите дату рождения (через пробел): ");
		
		int day = scanner.nextInt();
		int month = scanner.nextInt();
		int year = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Введите ФИО человека: ");
		
		String fullName = scanner.nextLine();
		
		return new Birthday(day, month, year, fullName);
		
	}
	
	public void deleteElem(Scanner scanner) {
		
		printArrayBirthday();
		
		System.out.print("\nВведите номер записи, которую хотите удалить: ");
		int deleteIndexElem = scanner.nextInt() - 1;
		
		if (deleteIndexElem < 0 || deleteIndexElem >= birthdays.size()) {
			
			System.err.println("Неверный номер записи!");
			
		} else {
			
			Birthday deleteElem = birthdays.get(deleteIndexElem);
			
			String deleteSQL = "delete from BirthdaysInfo where fullName = ? and dayBirthday = ? and monthBirthday = ? and yearBirthday =?";
			
			try(Connection connection = H2DataBase.connect();
					PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)){
				
				preparedStatement.setString(1, deleteElem.getFullName());
				preparedStatement.setInt(2, deleteElem.getDay());
				preparedStatement.setInt(3, deleteElem.getMonth());
				preparedStatement.setInt(4, deleteElem.getYear());
				
				preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
			    
				e.printStackTrace();
			    
			}
			
			birthdays.remove(deleteIndexElem);
			
			System.out.println("Запись удалена!");
			
		}

	}
	
	public void redactElem(Scanner scanner) {
		
		printArrayBirthday();
		
		System.out.print("\nВведите номер записи, которую хотите изменить: ");
		int redactIndexElem = scanner.nextInt() - 1;
		
		if (redactIndexElem < 0 || redactIndexElem >= birthdays.size()) {
			
			System.err.println("Неверный номер записи!");
			
		} else {
			
			Birthday redactElem = birthdays.get(redactIndexElem);
			
			String updateSQL = "update BirthdaysInfo set fullName = ?, dayBirthday = ?, monthBirthday = ?, yearBirthday =? where fullName = ? and dayBirthday = ? and monthBirthday = ? and yearBirthday =?";
			
			Birthday newElem = birthdayInfo(scanner);
			
			try(Connection connection = H2DataBase.connect();
					PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){
				
				preparedStatement.setString(1, newElem.getFullName());
				preparedStatement.setInt(2, newElem.getDay());
				preparedStatement.setInt(3, newElem.getMonth());
				preparedStatement.setInt(4, newElem.getYear());
				preparedStatement.setString(5, redactElem.getFullName());
				preparedStatement.setInt(6, redactElem.getDay());
				preparedStatement.setInt(7, redactElem.getMonth());
				preparedStatement.setInt(8, redactElem.getYear());
				
				preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
			    
				e.printStackTrace();
			    
			}
			
			birthdays.set(redactIndexElem, newElem);
			System.out.println("Запись изменена!");
			
		}
		
	}
	
	public void printArrayBirthday() {
		
		System.out.println("\n-----------");
		
		if (birthdays.size() < 1) {
			
			System.out.println("Список пуст!");
			
		} else {
			
			for (int i = 0; i < birthdays.size(); i++) {
			
				Birthday birthday = birthdays.get(i);
				
				System.out.println((i + 1) + ". " +birthday.fullInfoBirthday());
				
			}
			
		}

		System.out.println("-----------");
		
	}
	
	public Birthday getData() {
		
		String selectSQLBirthdayInfo = "select fullName, dayBirthday, monthBirthday, yearBirthday from BirthdaysInfo";
		
		try (Connection connection = H2DataBase.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQLBirthdayInfo)){
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				String fullName = resultSet.getString("fullName");
				int day = resultSet.getInt("dayBirthday");
				int month = resultSet.getInt("monthBirthday");
				int year = resultSet.getInt("yearBirthday");

				addElem(new Birthday(day, month, year, fullName));
			
				
			}
			
			connection.close();
			
		} catch (SQLException e) {
		    
			e.printStackTrace();
		    
		}
		
		return null;
		
	}
	
	public void setData(Scanner scanner) {
		
		String insertSQLInfo = "insert into BirthdaysInfo (id, fullName, dayBirthday, monthBirthday, yearBirthday) "
				+ "values (DEFAULT, ?, ?, ?, ?)";
		
		Birthday birthday = birthdayInfo(scanner);
		
		try(Connection connection = H2DataBase.connect();
				PreparedStatement preparedStatement = connection.prepareStatement(insertSQLInfo)){
			
			preparedStatement.setString(1, birthday.getFullName());
			preparedStatement.setInt(2, birthday.getDay());
			preparedStatement.setInt(3, birthday.getMonth());
			preparedStatement.setInt(4, birthday.getYear());
			
			preparedStatement.executeUpdate();
			
			addElem(birthday);
			
		} catch (SQLException e) {
        	
        	e.printStackTrace();
            
        }
		
	}
	
	public void upcomingBirthday() {
		
		LocalDate dateNow = LocalDate.now(); // 2025-02-06
		
		int dayNow = dateNow.getDayOfMonth();
		int monthNow = dateNow.getMonthValue();
		int count = 0;
		boolean foundToday = false;
		
		System.out.println("Сегодняшние дни рождения:\n-----------");
		
		Collections.sort(birthdays, new BirthdayComparator());
		
		for (Birthday birthday : birthdays) {
			
			if (birthday.getMonth() == monthNow && birthday.getDay() == dayNow) {
				
				System.out.println(birthday.fullInfoBirthday());
				foundToday = true;
				
			}
			
		}
		
		if (!foundToday) {
			
			System.out.println("Сегодня дней рождений нет.");
			
		}
		
		System.out.println("-----------\n");
		
		System.out.println("Ближайшие дни рождения:\n-----------");
		
		if (count < 5) {
		
			for (Birthday birthday : birthdays) {
				
				if ((birthday.getMonth() == monthNow && birthday.getDay() > dayNow) ||
				   (birthday.getMonth() > monthNow) ||
				   (monthNow == 12 && birthday.getMonth() == 1)) {
					
					System.out.println(birthday.fullInfoBirthday());
					count++;
					
				}
				
			}
			
			for (Birthday birthday : birthdays) {
				
				if ((birthday.getMonth() == monthNow && birthday.getDay() < dayNow) || (birthday.getMonth() < monthNow)) {
					
					System.out.println(birthday.fullInfoBirthday());
					count++;
					
				}
				
			}
		
		}
		
		if (birthdays.size() < 1) {
			
			System.out.println("Список пуст!");
			
		}
		
		System.out.println("-----------\n");
		
	}
	
}
