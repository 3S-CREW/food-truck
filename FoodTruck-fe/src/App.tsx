import { Button } from '@toss/tds-mobile';

const App = () => {
  return (
    <div
      style={{
        flex: 1,
        padding: '20px',
        backgroundColor: 'lightblue',
        height: '100vh',
      }}
    >
      <h1>렌더링 테스트</h1>

      <Button style={{ backgroundColor: 'blue', color: 'white' }}>버튼</Button>

      <Button>기본 버튼</Button>
    </div>
  );
};

export default App;
