def get_row(lst, x):
    coordinates = []

    for row_index, row in enumerate(lst):
        for column_index in range(len(row) - 1, -1, -1):
            if row[column_index] == x:
                coordinates.append((row_index, column_index))

    return coordinates