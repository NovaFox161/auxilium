package me.xaanit.auxilium.objects;

import com.google.gson.InstanceCreator;
import me.xaanit.auxilium.commands.BotInfo;
import me.xaanit.auxilium.interfaces.ICommand;

import java.lang.reflect.Type;

class ICommandInstanceCreator implements InstanceCreator<ICommand> {
  public ICommand createInstance(Type type) {
    return new BotInfo();
  }
}
