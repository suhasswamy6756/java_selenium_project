import csv

data = [
    ["suppi", "Suhas@suppi9060"],
    ["abdul", "Abdul@1234"],
    ["sudeep", "Sudeep@1234"]
    # Add more data sets as needed
]

csv_file_path = "testdata.csv"

with open(csv_file_path, mode='w', newline='') as file:
    writer = csv.writer(file)
    writer.writerows(data)

print(f"CSV file '{csv_file_path}' has been generated.")
