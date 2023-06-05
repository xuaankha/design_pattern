// Command
public interface Command {
    void execute();
    void undo();
}
@Component
public class SaveCommand implements Command {
    @Autowired
    private DataService dataService;

    private Data data;

    public SaveCommand(Data data) {
        this.data = data;
    }

    @Override
    public void execute() {
        dataService.save(data);
    }

    @Override
    public void undo() {
        dataService.delete(data);
    }
}

@Component
public class UpdateCommand implements Command {
    @Autowired
    private DataService dataService;

    private Data newData;
    private Data oldData;

    public UpdateCommand(Data newData, Data oldData) {
        this.newData = newData;
        this.oldData = oldData;
    }

    @Override
    public void execute() {
        dataService.update(newData);
    }

    @Override
    public void undo() {
        dataService.update(oldData);
    }
}

@Component
public class DeleteCommand implements Command {
    @Autowired
    private DataService dataService;

    private Data data;

    public DeleteCommand(Data data) {
        this.data = data;
    }

    @Override
    public void execute() {
        dataService.delete(data);
    }

    @Override
    public void undo() {
        dataService.save(data);
    }
}
@Component
public class CommandInvoker {
    private List<Command> commandHistory = new ArrayList<>();

    public void execute(Command command) {
        command.execute();
        commandHistory.add(command);
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.remove(commandHistory.size() - 1);
            command.undo();
        }
    }
}

@Component
public class DataService {
    public void save(Data data) {
        // Lưu dữ liệu vào cơ sở dữ liệu
    }

    public void update(Data data) {
        // Cập nhật dữ liệu trong cơ sở dữ liệu
    }

    public void delete(Data data) {
        // Xóa dữ liệu khỏi cơ sở dữ liệu
    }
}
@Autowired
private CommandInvoker commandInvoker;

@Autowired
private DataService dataService;

public void saveData(Data data) {
    Command saveCommand = new SaveCommand(data);
    commandInvoker.execute(saveCommand);
}

public void updateData(Data newData, Data oldData) {
    Command updateCommand = new UpdateCommand(newData, oldData);
    commandInvoker.execute(updateCommand);
}

public void deleteData(Data data) {
    Command deleteCommand = new DeleteCommand(data);
    commandInvoker.execute(deleteCommand);
}

public void undoLastCommand() {
    commandInvoker.undo();
}