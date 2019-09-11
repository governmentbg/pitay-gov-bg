package indexbg.pdoi.system;

import com.indexbg.system.SysConstants;

public class Constants extends SysConstants {

		public static final String beanMessages="beanMessages";
		public static final String LABELS="labels";
		
		
		// Кодове на обекти
		/**  ПОТРЕБИТЕЛ */
		public static final Long CODE_OBJECT_USER =  8L;
		
		/**	ЗАЯВЛЕНИЕ  */
		public static final Long CODE_OBJECT_APPLICATION = 13L;
		
		/**	СЪБИТИЕ  */
		public static final Long CODE_OBJECT_EVENT = 14L;
		
		/**	ПУБЛИКАЦИЯ  */
		public static final Long CODE_OBJECT_PUBLICATION = 15L;
		
		/**	ЗАДЪЛЖЕН СУБЕКТ  */
		public static final Long CODE_OBJECT_REQ_SUBJECT = 16L;	
		
		/**	ТЕМАТИКИ КЪМ ЗАЯВЛЕНИЕ  */
		public static final Long CODE_OBJECT_APP_THEMES = 17L;	
		
		/**	АБОНАМЕНТ  */
		public static final Long CODE_OBJECT_SUBSCRIPTION = 18L;	
		
		
		// Кодове на системни класификации	
		
		/**	 Код на системна класификация "Тип/Вече Вид/ потребител" */
		public static final Long CODE_SYSCLASS_TYPE_USER = 10L;
		
		/**	 Код на системна класификация "Тематика" */
		public static final Long CODE_SYSCLASS_THEMAS = 10008L;
		
		/**	 Код на системна класификация "Статус на заявление" */
		public static final Long CODE_SYSCLASS_STATUS_APPLICATION = 10009L;
		
		/**	 Код на системна класификация "Вид събитие" */
		public static final Long CODE_SYSCLASS_TYPE_EVENT = 10010L;
		
		/**	 Код на системна класификация "Статус на събитие" */
		public static final Long CODE_SYSCLASS_STATUS_EVENT = 10011L;
		
		/**	 Код на системна класификация "Причина за удължаване на срока" */
		public static final Long CODE_SYSCLASS_REASON_EXTENSION = 10012L;
		
		/**	 Код на системна класификация "Причина за отказ на достъп до обществена информация" */
		public static final Long CODE_SYSCLASS_REASON_DENY_DOI = 10013L;		
		
		/**	 Код на системна класификация "Тип заявител" */
		public static final Long CODE_SYSCLASS_TYPE_APPLICANT = 10014L;
		
		/**	 Код на системна класификация "Тип на връзка" */
		public static final Long CODE_SYSCLASS_TYPE_RELATION = 10015L;
		
		/**	 Код на системна класификация "Удостоверяващ орган" */
		public static final Long CODE_SYSCLASS_CERTIF_MEMBER = 10016L;
		
		/**	 Код на системна класификация "Държави" */
		public static final Long CODE_SYSCLASS_COUNTRIES = 10017L;
		
		/**	 Код на системна класификация "Секции" */
		public static final Long CODE_SYSCLASS_SECTION = 10019L;
		
		/**	 Код на системна класификация "Крайно решение" */
		public static final Long CODE_SYSCLASS_FINAL_DECISION = 10021L;
		
		/**	 Код на системна класификация "Видове задължени субекти" */
		public static final Long CODE_SYSCLASS_VID_SUBJECT = 10024L;
		
		/**	 Код на системна класификация "Административен регистър" */
		public static final Long CODE_SYSCLASS_ADM_REGISTRY = 10025L;
		
		
		/**	 Код на системна класификация "EKATTE" */
		public static final Long CODE_SYSCLASS_EKATTE = 10026L;
		
		
		
		
		
		//Системна класификация  - Тип/Вече Вид/ потребител
		/** код на значение Тип потребител - Модератор 
		    Кода е фиктивен(няма го в класификацията) но за да разпознаваме кой е модератор кой адми ни трябва*/
		public static final long CODE_ZNACHENIE_TYPE_USER_MODERATOR = -1; 
		
		
		//Системна класификация Статус потребител  - 9
		public static final long CODE_ZNACHENIE_STATUS_POTREB_ACTIVEN = 2;
		
		
		
		//Системна класификация 10008 - Тематика
		
		//Системна класификация 10009 - Статус на заявление
		/** код на значение Статус на заявление - Прието на платформата */
		public static final long CODE_ZNACHENIE_STATUS_APP_ACCEPTED_PLATFORM = 1;
		/** код на значение Статус на заявление - Очаква регистрация при задължен субект */
		public static final long CODE_ZNACHENIE_STATUS_APP_EXPECTED_REG = 2;
		/** код на значение Статус на заявление - Регистрирано/в процес на обработка */
		public static final long CODE_ZNACHENIE_STATUS_APP_REGISTERED = 3;
		/** код на значение Статус на заявление - Одобрено */
		public static final long CODE_ZNACHENIE_STATUS_APP_APPROVED = 4;
		/** код на значение Статус на заявление - Частично одобрено */
		public static final long CODE_ZNACHENIE_STATUS_APP_PART_APPROVED = 5;
		/** код на значение Статус на заявление - Неодобрено */
		public static final long CODE_ZNACHENIE_STATUS_APP_NON_APPROVED = 6;
		/** код на значение Статус на заявление - Информацията не съществува */
		public static final long CODE_ZNACHENIE_STATUS_APP_INFO_NOT_EXIST = 7;
		/** код на значение Статус на заявление - Оставено без разглеждане */
		public static final long CODE_ZNACHENIE_STATUS_APP_LEFT_WITHOUT_CONSID = 8;
				
		//Системна класификация 10010 - Вид събитие
		 
		/** код на значение Вид събитие - Искане на допълнителна информация */
		public static final long CODE_ZNACHENIE_TYPE_EVENT_REQ_ADD_INFORMATION = 2;
		/** код на значение Вид събитие - Предоставяне на допълнителна информация */
		public static final long CODE_ZNACHENIE_TYPE_EVENT_PROVID_ADD_INFORMATION = 3;
		/** код на значение Вид събитие - Препращане на заявление */
		public static final long CODE_ZNACHENIE_TYPE_EVENT_FORW_APPLICATION = 4;
		/** код на значение Вид събитие - Удължаване на срока */
		public static final long CODE_ZNACHENIE_TYPE_EVENT_EXTEND_TERM = 5;
		/** код на значение Вид събитие - Крайно решение */
		public static final long CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION = 6;
		/** код на значение Вид събитие - Изпратено към деловодна система */
		public static final long CODE_ZNACHENIE_TYPE_EVENT_SEND_TO_SYSTEM = 7;
		/** код на значение Вид събитие - Потвърждение от деловодна система */
		public static final long CODE_ZNACHENIE_TYPE_EVENT_CONFIRM_FROM_SYSTEM = 8;
		/** код на значение Вид събитие - Подаване на заявление */
		public static final long CODE_ZNACHENIE_TYPE_EVENT_SUBMIT_APP = 9;
		
		//Системна класификация 10011 - Статус на събитие
		/** код на значение Статус на събитие - Изпълнено */
		public static final long CODE_ZNACHENIE_STATUS_EVENT_COMPLETED = 1;
		/** код на значение Статус на събитие - Неизпълнено */
		public static final long CODE_ZNACHENIE_STATUS_EVENT_UNCOMPLETED = 2;
		
		//Системна класификация 10012 - Причина за удължаване на срока
		/** код на значение Причина за удължаване на срока - Удължаване на срока поради запитване до трето лице */
		public static final long CODE_ZNACHENIE_REASON_EXTENSION_REQ_THIRD_PART = 1;
		/** код на значение Причина за удължаване на срока - Удължаване на срока поради голямо количество информация */
		public static final long CODE_ZNACHENIE_REASON_EXTENSION_LARGE_INFO = 2;
		
		//Системна класификация 10013 - Причина за отказ на достъп до обществена информация
		/** код на значение Причина за отказ на достъп до обществена информация - Информацията е класифицирана или друга защитена тайна */
		public static final long CODE_ZNACHENIE_REASON_DENY_DOI_CLASSIF_OR_SECRET = 1;
		/** код на значение Причина за отказ на достъп до обществена информация - Информацията засяга интересите на трето лице, което е отказало предоставяне */
		public static final long CODE_ZNACHENIE_REASON_DENY_DOI_CONCERNS_INTERESTS_THIRD_PART = 2;
		/** код на значение Причина за отказ на достъп до обществена информация - Информацията е предоставена на заявителя през предходните 6 месеца */
		public static final long CODE_ZNACHENIE_REASON_DENY_DOI_PROVIDE_INFO_PREV_SIX_MONTHS = 3;
		
		//Системна класификация 10014 - Тип заявител 
		/** код на значение Тип заявител  - Физическо лице */
		public static final long CODE_ZNACHENIE_TYPE_APPLICANT_FIZ_LICE = 1;
		/** код на значение Тип заявител  - Юридическо лице */
		public static final long CODE_ZNACHENIE_TYPE_APPLICANT_URID_LICE = 2;
		
		//Системна класификация 10015 - Тип на връзка
		/** код на значение Тип на връзка - Абонамент */
		public static final long CODE_ZNACHENIE_TYPE_RELATION_SUBSCRIPTION = 1;
		/** код на значение Тип на връзка - Лица за контакт */
		public static final long CODE_ZNACHENIE_TYPE_RELATION_CONTACT_PERSONS = 2;
		/** код на значение Тип на връзка - Член на група */
		public static final long CODE_ZNACHENIE_TYPE_RELATION_GROUP_MEMBER = 3;
		
		//Системна класификация 10016 - Удостоверяващ орган
		/** код на значение Удостоверяващ орган - Информационно обслужване */
		public static final long CODE_ZNACHENIE_CERTIF_MEMBER_INFO_SERVICES = 1;
		/** код на значение Удостоверяващ орган - Банксервиз */
		public static final long CODE_ZNACHENIE_CERTIF_MEMBER_BANKSERVICE = 2;
		/** код на значение Удостоверяващ орган - Инфонотари */
		public static final long CODE_ZNACHENIE_CERTIF_MEMBER_INFONOTARY = 3;
		/** код на значение Удостоверяващ орган - Спектър */
		public static final long CODE_ZNACHENIE_CERTIF_MEMBER_SPEKTAR = 4;
		/** код на значение Удостоверяващ орган - СЕП България */
		public static final long CODE_ZNACHENIE_CERTIF_MEMBER_SEP_BULGARIA = 5;
		
		//Системна класификация 10017 - Държави
		/** код на значение България */
		public static final long CODE_ZNACHENIE_BG = 38;
		
		//Системна класификация 10019 - Секции  
		/** код на значение Информация за потребителя */
		public static final long CODE_ZNACHENIE_SECT_USER_INFO = 2;
		/** код на значение Статистическа информация */
		public static final long CODE_ZNACHENIE_SECT_STAT_INFO = 5;
		/** код на значение Контакти */
		public static final long CODE_ZNACHENIE_SECT_CONTACTS = 4;
		/** код на значение Документи и полезни връзки */
		public static final long CODE_ZNACHENIE_SECT_DOCUMENTS = 1;
		/** код на значение Начална страница */
		public static final long CODE_ZNACHENIE_SECT_HOME = 6;	
		
		//Системна класификация 10024 - Видове задължени субекти
		/** код на значение ДРУГИ */
		public static final Long CODE_ZNACHENIE_DRUGI_SUBJECT = 12L;
		
		// Кодове на значения за извикване на шаблони за изпращане на уведомитени съобщения по мейл
		/** код на значение шаблон Подаване на заявление до заявител при ЗДОИ със СОЕС */
		public static final long CODE_ZNACHENIE_SHABLON_PODAVANE_APPLIC_ZDOI_S_SOES = 1;
		/** код на значение шаблон Подаване на заявление до заявител при ЗДОИ без СОЕС */
		public static final long CODE_ZNACHENIE_SHABLON_PODAVANE_APPLIC_ZDOI_BEZ_SOES = 2;
		/** код на значение шаблон Подаване на заявление до админ при ЗДОИ без СОЕС */
		public static final long CODE_ZNACHENIE_SHABLON_PODAVANE_ADMIN_ZDOI_BEZ_SOES = 3;
		/** код на значение шаблон Потвърждение от деловодна система до заявител при ЗДОИ със СОЕС */
		public static final long CODE_ZNACHENIE_SHABLON_POTV_APPLIC_ZDOI_S_SOES = 4;
		/** код на значение шаблон Потвърждение от деловодна система до админ при ЗДОИ със СОЕС */
		public static final long CODE_ZNACHENIE_SHABLON_POTV_ADMIN_ZDOI_S_SOES = 5;
		/** код на значение шаблон Препращане на заявление до заявител */
		public static final long CODE_ZNACHENIE_SHABLON_PREPRASHTANE_APPLIC = 6;
		/** код на значение шаблон Препращане на заявление до админ при ЗДОИ без СОЕС */
		public static final long CODE_ZNACHENIE_SHABLON_PREPRASHTANE_ADMIN_ZDOI_BEZ_SOES = 7;
		/** код на значение шаблон Искане на допълнителна информация до заявител */
		public static final long CODE_ZNACHENIE_SHABLON_ISKANE_DOP_INFO_OT_APPLIC = 8;
		/** код на значение шаблон Предоставяне на допълнителна информация до админ при ЗДОИ без СОЕС */
		public static final long CODE_ZNACHENIE_SHABLON_PREDOSTAV_DOP_INFO_DO_ADMIN_ZDOI_BEZ_SOES = 9;
		/** код на значение шаблон Удължаване на срока - отнасяне до трето лице до заявител */
		public static final long CODE_ZNACHENIE_SHABLON_UDALJAVANE_SROK_TRETO_LICE_DO_APPLIC = 10;
		/** код на значение шаблон Удължаване на срока - голямо количество информация до заявител */
		public static final long CODE_ZNACHENIE_SHABLON_UDALJAVANE_SROK_GOL_KOLICH_INFO_DO_APPLIC = 11;
		/** код на значение шаблон Крайно решение до заявител */
		public static final long CODE_ZNACHENIE_SHABLON_KRAINO_RESHENIE_DO_APPLIC = 12;
		/** код на значение шаблон Техническа грешка - при изтриване на събитие */
		public static final long CODE_ZNACHENIE_SHABLON_DELETE_EVENT_DO_APPLIC = 13;
		/** код на значение шаблон Абонамент за заявление до абониран при препращане  */
		public static final long CODE_ZNACHENIE_SHABLON_PREPRASHTANE_SUBSCRIBED = 14;
		/** код на значение шаблон Абонамент за заявление до абониран при Искане на допълнителна информация */
		public static final long CODE_ZNACHENIE_SHABLON_ISKANE_DOP_INFO_SUBSCRIBED = 15;
		/** код на значение шаблон Абонамент за заявление до абониран при Удължаване на срока - отнасяне до трето лице до заявител */
		public static final long CODE_ZNACHENIE_SHABLON_UDALJAVANE_SROK_TRETO_LICE_SUBSCRIBED = 16;
		/** код на значение шаблон Абонамент за заявление до абониран при Удължаване на срока - голямо количество информация до заявител */
		public static final long CODE_ZNACHENIE_SHABLON_UDALJAVANE_SROK_GOL_KOLICH_INFO_SUBSCRIBED = 17;
		/** код на значение шаблон Абонамент за заявление до абониран при Крайно решение */
		public static final long CODE_ZNACHENIE_SHABLON_KRAINO_RESHENIE_SUBSCRIBED = 18;
		/** код на значение шаблон Абонамент за заявление до абониран при Потвърждение от деловодна система при ЗДОИ със СОЕС */
		public static final long CODE_ZNACHENIE_SHABLON_POTV_SUBSCRIBED_ZDOI_S_SOES = 19;
		/** код на значение шаблон Абонамент за заявление до абониран при Техническа грешка - при изтриване на събитие */
		public static final long CODE_ZNACHENIE_SHABLON_DELETE_EVENT_SUBSCRIBED = 20;
		
		
		
		
}
