def generate_inserts():
    with open("inserts1.sql", "w") as f:
        f.write("-- Insert statements for Employee table\\n\\n")
        for i in range(1, 1001):
            first_name = f"FirstName{i}"
            last_name = f"LastName{i}"
            birth_year = 1950 + (i % 50)
            salary = 50000 + (i % 50) * 1000
            f.write(f"INSERT INTO employees (id, first_name, last_name, birth_year, salary) VALUES ({i}, '{first_name}', '{last_name}', {birth_year}, {salary});")
            # f.write(f"({i}, '{first_name}', '{last_name}', {birth_year}, {salary});")

generate_inserts()
