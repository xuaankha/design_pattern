public interface Handler {
    void setNext(Handler handler);
    void handleRequest(Request request);
}
public abstract class AbstractHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(Request request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
@Component
public class AuthenticationHandler extends AbstractHandler {
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == RequestType.AUTHENTICATION) {
            // Xử lý yêu cầu authentication
        } else {
            super.handleRequest(request);
        }
    }
}

@Component
public class AuthorizationHandler extends AbstractHandler {
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == RequestType.AUTHORIZATION) {
            // Xử lý yêu cầu authorization
        } else {
            super.handleRequest(request);
        }
    }
}

@Component
public class LoggingHandler extends AbstractHandler {
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == RequestType.LOGGING) {
            // Xử lý yêu cầu logging
        } else {
            super.handleRequest(request);
        }
    }
}
@Component
public class RequestProcessor {
    @Autowired
    private AuthenticationHandler authenticationHandler;

    @Autowired
    private AuthorizationHandler authorizationHandler;

    @Autowired
    private LoggingHandler loggingHandler;

    public void processRequest(Request request) {
        authenticationHandler.setNext(authorizationHandler);
        authorizationHandler.setNext(loggingHandler);
        authenticationHandler.handleRequest(request);
    }
}