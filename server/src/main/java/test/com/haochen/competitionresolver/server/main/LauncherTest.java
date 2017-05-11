package test.com.haochen.competitionresolver.server.main;

import com.haochen.competitionresolver.server.command.CommandHandler;
import com.haochen.competitionresolver.server.impl.network.socket.SocketMonitor;
import com.haochen.competitionresolver.server.main.CommandLine;
import com.haochen.competitionresolver.server.main.Launcher;
import com.haochen.competitionresolver.server.network.NetworkMonitor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Launcher Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>四月 2, 2017</pre>
 */
public class LauncherTest {

    private NetworkMonitor monitor = new SocketMonitor();
    private Launcher launcher = new Launcher(monitor);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: execute(String ins)
     */
    @Test
    public void testExecute() throws Exception {
//TODO: Test goes here...
        {
            Assert.assertEquals(true, CommandHandler.INSTANCE.isEnable());
        }
        {
            launcher.execute(CommandLine.Command.HANDLER_STOP);
            Assert.assertEquals(false, CommandHandler.INSTANCE.isEnable());
        }
        {
            launcher.execute(CommandLine.Command.HANDLER_START);
            Assert.assertEquals(true, CommandHandler.INSTANCE.isEnable());
        }
    }
} 
