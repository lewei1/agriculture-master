package org.zcy.agriculture.operate.common.exception.file;

import org.zcy.agriculture.operate.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author numberone
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
