package me.xaanit.auxilium.gson.instance;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import me.xaanit.auxilium.commands.*;
import me.xaanit.auxilium.interfaces.ICommand;

public class BotinfoI implements InstanceCreator<ICommand> {
  public ICommand createInstance(Type type) {
    return new Botinfo();
  }
}

