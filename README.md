# SuperUser

A simple paper plugin that allows you to switch in and out of a superuser mode.

Upon entering superuser mode the player's position, inventory, health, etc. will be saved and restored upon exiting
superuser mode.

## Plugin configuration

A sample plugin configuration is given below

```yaml
# For both sets of commands %p will be replaced with the player's name and %w with the player's world
# Commands to be executed when entering superuser mode
enterCommands:
  - "op %p"
  - "gamemode creative %p"
# Commands to be executed when exiting superuser mode
exitCommands:
  - "deop %p"
  - "gamemode survival %p"
```

## Plugin permissions

* `superuser.commands.sudo` - Allows a player to use the `/sudo` command (aliased to `/duty`) to enter/exit super user
  mode

## Support Me

I will **never** charge money for the use of my plugins, however they do require a significant amount of work to
maintain and update. If you'd like to show your support and buy me a cup of tea sometime (I don't drink that horrid
coffee stuff :P) you can do so [here](https://www.paypal.me/zerthick)