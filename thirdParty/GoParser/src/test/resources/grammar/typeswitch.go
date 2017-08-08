package test

func swtitchTest() {
   	switch s := src.(type) {
   	case []byte:
   		return nil
   	case string:
   		return nil
   	case time.Time:
   		return nil
   	case nil:
   		return nil
   	}
}
