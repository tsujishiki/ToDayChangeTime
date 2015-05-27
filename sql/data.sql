insert into dictionary (`category`,`name`,`code`,`enable`,`desc`) values
  ('游戏类型','动作游戏','ACT',1,''),
  ('游戏类型','冒险游戏','AVG',1,''),
  ('游戏类型','第一人称视点射击游戏','FPS',1,''),
  ('游戏类型','格斗游戏','FTG',1,''),
  ('游戏类型','音乐游戏 ','MUG',1,''),
  ('游戏类型','益智类游戏','PUZ',1,''),
  ('游戏类型','赛车游戏','RAC',1,''),
  ('游戏类型','角色扮演游戏','RPG',1,''),
  ('游戏类型','即时战略游戏','RTS',1,''),
  ('游戏类型','模拟/战棋式战略游戏','SLG',1,''),
  ('游戏类型','体育运动游戏','SPG',1,''),
  ('游戏类型','射击游戏','STG',1,''),
  ('游戏类型','桌面游戏','TAB',1,''),
  ('游戏类型','其他类型游戏','ETC',1,''),
  ('游戏类型','恋爱养成游戏','AVG/ADV',1,''),
  ('游戏类型','其他','O',1,'');

insert into dictionary (`category`,`name`,`code`,`enable`,`desc`) values
  ('游戏平台','PS4','PS4',1,''),
  ('游戏平台','PS3','PS3',1,''),
  ('游戏平台','PS2','PS',1,''),
  ('游戏平台','PS','PS',1,''),
  ('游戏平台','XBOXONE','XBOXONE',1,''),
  ('游戏平台','XBOX360','XBOX360',1,''),
  ('游戏平台','XBOX','XBOX',1,''),
  ('游戏平台','WIIU','WIIU',1,''),
  ('游戏平台','WII','WII',1,''),
  ('游戏平台','PSV ','PSV',1,''),
  ('游戏平台','PSP','PSP',1,''),
  ('游戏平台','3DS','3DS',1,''),
  ('游戏平台','NDS','NDS',1,''),
  ('游戏平台','PC','PC',1,''),
  ('游戏平台','SS','SS',1,''),
  ('游戏平台','MD','MD',1,''),
  ('游戏平台','GBC','GBC',1,''),
  ('游戏平台','GBA','GBA',1,''),
  ('游戏平台','其他','O',1,'');

insert into dictionary (`category`,`name`,`code`,`enable`,`desc`) values
  ('区域','亚洲','AS',1,'Asia'),
  ('区域','欧洲','EU',1,'Europe'),
  ('区域','美洲','AM',1,'America'),
  ('区域','澳洲','AU',1,'Australia'),
  ('区域','大陆','CHN',1,'Mainland Of China'),
  ('区域','香港','UK',1,'Hongkong'),
  ('区域','台湾','TW',1,'Taiwan'),
  ('区域','日本','JP',1,'Japan'),
  ('区域','韩国','ROK',1,'Republic of Korea'),
  ('区域','其他','O',1,'other');


INSERT INTO `db_tdct`.`game`(`gameName`,`enName`,`jpName`,`typeId`,`maker`,`publisher`,`platformId`,`regionId`) VALUES
  ('暗黑破坏神III','DIABLO Ⅲ',null,(select dictid from dictionary where name='角色扮演游戏'),'Blizzard','Blizzard',(select dictid from dictionary where name='PS4'),(select dictid from dictionary where name='亚洲')),
  ('暗黑破坏神III','DIABLO Ⅲ',null,(select dictid from dictionary where name='角色扮演游戏'),'Blizzard','Blizzard',(select dictid from dictionary where name='PS3'),(select dictid from dictionary where name='亚洲')),
  ('暗黑破坏神III','DIABLO Ⅲ',null,(select dictid from dictionary where name='角色扮演游戏'),'Blizzard','Blizzard',(select dictid from dictionary where name='XBOXONE'),(select dictid from dictionary where name='亚洲')),
  ('暗黑破坏神III','DIABLO Ⅲ',null,(select dictid from dictionary where name='角色扮演游戏'),'Blizzard','Blizzard',(select dictid from dictionary where name='PC'),(select dictid from dictionary where name='亚洲')),
  ('侠盗猎车手5','Grand Theft Auto V',null,(select dictid from dictionary where name='动作游戏'),'Rockstar North','Rockstar Games',(select dictid from dictionary where name='PS4'),(select dictid from dictionary where name='亚洲')),
  ('侠盗猎车手5','Grand Theft Auto V',null,(select dictid from dictionary where name='动作游戏'),'Rockstar North','Rockstar Games',(select dictid from dictionary where name='PC'),(select dictid from dictionary where name='香港'));