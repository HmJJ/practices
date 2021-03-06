模式的定义与特点
	定义：
		指将一个复杂对象的构造与他的表示分离，使同样的构建过程可以创建不同的表示。

	优点：
		1、各个具体的建造者相互独立，有利于系统的扩展。
		2、客户端不必知道产品内部组成的细节，便于控制细节风险。

	缺点：
		1、产品的组成部分必须相同，这限制了其使用范围。
		2、如果产品的内容变化复杂，该模式会增加很多的建造者类。

	建造者模式和工厂模式的关注点不同：建造者模式注重零部件的组装过程，而工厂方法模式更注重零部件的创建过程，但两者可以结合使用。

模式的结构与实现
	建造者模式由产品、抽象建造者、具体建造者、指挥者等4个要素构成。

	模式的结构：
		1、产品角色：他是包含多个组成部件的复杂对象，由具体建造者来创建其各个子部件。
		2、抽象建造者：他是一个包含创建产品各个子部件的抽象方法的接口，统长还包含一个返回复杂返聘的方法getResult()。
		3、具体建造者：实现Builder接口，完成复杂产品的各个部件的具体创建方法。
		4、指挥者：它调用创造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息。

模式的扩展
    建造者模式在应用过程中可以根据需要改变，如果创建的产品种类只有一种，只需要一个具体建造者，这时可以省略掉抽象建造者，甚至可以省略掉指挥者角色。