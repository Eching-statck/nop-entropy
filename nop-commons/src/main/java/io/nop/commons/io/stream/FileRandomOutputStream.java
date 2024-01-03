/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.nop.commons.io.stream;

// 从esProc项目拷贝的代码

import io.nop.api.core.exceptions.NopException;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.OverlappingFileLockException;

/**
 * 可以改变输出位置的输出流
 *
 * @author WangXiaoJun
 */
public class FileRandomOutputStream extends RandomOutputStream {
    public static final int LOCK_SLEEP_TIME = 100; // 锁休眠时间

    private RandomAccessFile file;
    private FileChannel channel;

    /**
     * 由RandomAccessFile创建可以改变输出位置的输出流
     *
     * @param file RandomAccessFile
     */
    public FileRandomOutputStream(RandomAccessFile file) {
        this.file = file;
    }

    /**
     * 取文件管道
     *
     * @return FileChannel
     */
    public FileChannel getChannel() {
        if (channel == null) {
            channel = file.getChannel();
        }

        return channel;
    }

    /**
     * 取输入流
     *
     * @param pos 文件位置
     */
    public InputStream getInputStream(long pos) throws IOException {
        FileChannel channel = getChannel();
        channel.position(pos);
        return new ChannelInputStream(channel, true);
    }

    /**
     * 锁定文件，防止被另一个线程修改
     */
    public boolean tryLock() throws IOException {
        return getChannel().tryLock() != null;
    }

    /**
     * 锁定文件，防止被另一个线程修改
     */
    public boolean lock() throws IOException {
        FileChannel channel = getChannel();
        while (true) {
            try {
                channel.lock();
                return true;
            } catch (OverlappingFileLockException e) {
                try {
                    Thread.sleep(LOCK_SLEEP_TIME);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw NopException.adapt(e);
                }
            } catch (Exception e) {
                throw NopException.adapt(e);
            }
        }
    }

    /**
     * 设置下一步的输出位置
     *
     * @param newPosition 位置
     */
    public void position(long newPosition) throws IOException {
        file.seek(newPosition);
    }

    /**
     * 返回当前的输出位置
     *
     * @return long 位置
     */
    public long position() throws IOException {
        return file.getFilePointer();
    }

    /**
     * 写入一个字节
     *
     * @param b 字节值
     */
    public void write(int b) throws IOException {
        file.write(b);
    }

    /**
     * 写入一个字节数组
     *
     * @param b 字节数组
     */
    public void write(byte b[]) throws IOException {
        file.write(b);
    }

    /**
     * 写入一个字节数组
     *
     * @param b   字节数组
     * @param off 起始位置
     * @param len 长度
     */
    public void write(byte b[], int off, int len) throws IOException {
        file.write(b, off, len);
    }

    /**
     * 关闭输出流
     */
    public void close() throws IOException {
        file.close();
    }
}