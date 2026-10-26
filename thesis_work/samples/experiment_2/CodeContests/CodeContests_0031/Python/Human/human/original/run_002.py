from sys import stdin, stdout

class Block:
    def __init__(self, id, offset, size):
        self.id = id
        self.offset = offset
        self.size = size

    def __str__(self):
        return "{0} {1} {2}".format(self.id, self.offset, self.size)


class MemoryManager:
    def __init__(self, capacity):
        self.capacity = capacity
        self.blocks = [Block(-1, 0, 0), Block(-1, 100, 0)]
        self.last_block_id = 0

    def get_available_position(self, amount):
        prev_block = self.blocks[0]
        for i in range(1, len(self.blocks)):
            curr_block = self.blocks[i]
            avail_offset = prev_block.offset + prev_block.size
            avail_amount = curr_block.offset - avail_offset
            if avail_amount >= amount and avail_offset + amount <= capacity:
                return i
            prev_block = curr_block
        return None

    def allocate(self, amount):
        avail_position = self.get_available_position(amount)
        if avail_position == None:
            return None
        self.last_block_id += 1
        prev_block = self.blocks[avail_position - 1]
        self.blocks.insert(avail_position, Block(self.last_block_id, prev_block.offset + prev_block.size, amount))
        return self.last_block_id

    def erase(self, block_id):
        for block in self.blocks:
            if block.id == block_id and block_id != -1:
                self.blocks.remove(block)
                return True
        return False

    def defragment(self):
        block_count = len(self.blocks)
        prev_block = self.blocks[0]
        for i in range(1, block_count):
            curr_block = self.blocks[i]
            curr_block.offset = prev_block.offset + prev_block.size
            prev_block = curr_block
        self.blocks[block_count - 1].offset = 100


class MemoryManagerConsole:
    def __init__(self, memory_manager: MemoryManager):
        self.memory_manager = memory_manager

    def read(self):
        input = stdin.readline().rstrip().split()
        command = input[0]
        if command == 'alloc':
            amount = int(input[1])
            block_id = self.memory_manager.allocate(amount)
            # self.print_blocks()
            return str(block_id or 'NULL')
        elif command == 'erase':
            block_id = int(input[1])
            if self.memory_manager.erase(block_id) == False:
                # self.print_blocks()
                return 'ILLEGAL_ERASE_ARGUMENT'
            # self.print_blocks()
        elif command == 'defragment':
            self.memory_manager.defragment()
            # self.print_blocks()
        return None

    def print_blocks(self):
        for block in self.memory_manager.blocks:
            stdout.write(str(block) + "\n")


command_count, capacity = map(int, stdin.readline().rstrip().split())
memory_manager = MemoryManager(capacity)
manager_console = MemoryManagerConsole(memory_manager)
output_batch = list()
for i in range(command_count):
    result = manager_console.read()
    if result != None:
        output_batch.append(result)

for output in output_batch:
    stdout.write(output + "\n")