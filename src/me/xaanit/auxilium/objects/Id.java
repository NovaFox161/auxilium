package me.xaanit.auxilium.objects;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import me.xaanit.auxilium.commands.Botinfo;
import me.xaanit.auxilium.interfaces.ICommand;

class ICommandInstanceCreator implements InstanceCreator<ICommand> {
  public ICommand createInstance(Type type) {
    return new Botinfo();
  }
}
