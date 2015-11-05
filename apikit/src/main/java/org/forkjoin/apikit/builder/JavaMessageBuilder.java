package org.forkjoin.apikit.builder;

import com.forkjoin.core.ResultExecute;
import org.forkjoin.apikit.Config;
import org.forkjoin.apikit.model.MessageInfo;
import org.forkjoin.apikit.model.ModelInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zuoge85 on 15/6/14.
 */
public class JavaMessageBuilder extends Builder<MessageInfo> {
    private static final Logger log = LoggerFactory.getLogger(JavaMessageBuilder.class);

    public JavaMessageBuilder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage) {
        super(config, modelInfo, srcPath, rootPackage);
    }

    @Override
    protected void forEach(ResultExecute<Void, MessageInfo> resultExecute) {
        log.info("开始生成message");
        super.forEachMessage(resultExecute);
    }

    @Override
    protected String getSuffix() {
        return ".java";
    }

    @Override
    protected BuilderUtils getUtils(MessageInfo info) {
        return new JavaMessageUtils(config, modelInfo, info, rootPackage);
    }

    @Override
    protected String getTemplPath() {
        return "/org/forkjoin/apikit/templ/JavaMessage.httl";
    }
}
