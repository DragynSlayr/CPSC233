  if self.trigger.phase == 1 then
    self.x_speed = 0
    self.y_speed = 0
	
	self.rot_x, self.rot_y = MathHelper.rotate(self.x - self.last_x, self.y - self.last_y, math.pi / 10)
	
	self.x = self.rot_x + Player.x
	self.y = self.rot_y + Player.y
  elseif self.trigger.phase == 2 then